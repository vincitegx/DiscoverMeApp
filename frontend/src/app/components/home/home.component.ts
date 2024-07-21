import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Input, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { BehaviorSubject, Observable, catchError, map, of, startWith, tap } from 'rxjs';
import { AppState } from 'src/app/dtos/app-state';
import { Project } from 'src/app/dtos/project';
import { Socials } from 'src/app/dtos/socials';
import { DataState } from 'src/app/enums/data-state';
import { ProjectService } from 'src/app/services/project.service';
import { SocialService } from 'src/app/services/social.service';
import { environment } from 'src/environments/environment.development';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit,AfterViewInit {
  @ViewChild('videoElement') videoElement!: ElementRef<HTMLVideoElement>;
  private readonly apiServerUrl: string;
  private readonly notifier: NotifierService;
  readonly DataState = DataState;
  isProjectLimitReached$: Observable<boolean> = of();
  pages: Array<number> = [];
  page: number = 0;
  @Input() currentPage: number = 1;
  @Input() totalPages: number = 10;
  fileurl: string;
  projects$: Observable<Array<Project>>;
  appState$: Observable<AppState<any>> = of();
  search: string;
  sort:string;
  searchForm: FormGroup;
  private dataSubject = new BehaviorSubject<Array<Project>>([]);
  public socials: Array<Socials> = [];
  public defaultSelectedPlatform: number = 1;
  constructor(private projectService: ProjectService, private router: Router,
    notifierService: NotifierService, private socialService: SocialService) {
    this.apiServerUrl = environment['api-base-url'];
    this.fileurl = this.apiServerUrl + "api/v1/projects/contents";
    this.projects$ = of([]);
    this.search = "";
    this.sort = "default";
    this.searchForm = new FormGroup({
      search: new FormControl('', [Validators.required]),
    });
    this.notifier = notifierService;
  }

  ngAfterViewInit() {
    const video = this.videoElement.nativeElement;

    video.addEventListener('loadedmetadata', () => {
      this.adjustVideoSize(video);
    });
  }

  adjustVideoSize(video: HTMLVideoElement) {
    const cardWidth = video.parentElement?.clientWidth ?? 220;
    const videoAspectRatio = video.videoWidth / video.videoHeight;
    const cardAspectRatio = cardWidth / 220; // Assuming card height is 220px

    if (videoAspectRatio > cardAspectRatio) {
      // Video is wider than the card
      video.style.width = '100%';
      video.style.height = 'auto';
    } else {
      // Video is taller than the card
      video.style.width = 'auto';
      video.style.height = '100%';
    }

    const heightDifference = video.clientHeight - 220;
    if (heightDifference > 0) {
      video.style.marginTop = `-${heightDifference / 2}px`;
    }
  }

  ngOnInit(): void {
    this.loadData(this.search, this.page, this.sort);
    this.isProjectLimitReached$ = this.projectService.isProjectLimitExceeded().pipe(map(res => { return res }));
  }

  private loadData(search: string, page: number, sort: string): void {
    this.appState$ = this.projectService.getProjects(search, page, sort)
      .pipe(
        map(response => {
          const content = response['content'];
          this.dataSubject.next(content);
          this.pages = new Array(response['totalPages']);
          const dataState = content.length === 0 ? DataState.EMPTY_STATE : DataState.LOADED_STATE;
          return { dataState, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
  }

  playVideo(videoElement: HTMLVideoElement): void {
    if (videoElement) {
      videoElement.play();
    }
  }

  pauseVideo(videoElement: HTMLVideoElement): void {
    if (videoElement) {
      videoElement.pause();
      videoElement.currentTime = 0;
    }
  }

  toggleReaction(project: Project) {
    project.reacted = !project.reacted;
    this.projectService.toggleReaction(project.id ?? 0).subscribe(
      (updatedProject: Project) => {
        console.log(`Reaction status updated for project with ID ${project.id}`);
        project.noOfReaction = updatedProject.noOfReaction;
      },
      error => {
        console.error('Error updating reaction status:', error);
        project.reacted = !project.reacted;
      }
    );
  }

  onSupportButtonClick(project: Project): void {
    const socialName = project.social?.name ?? "";
    if (this.socialService.isSocialConnected(socialName)) {
      project.supported = !project.supported;
      this.projectService.updateSupportStatus(project.id ?? 0).subscribe(
        () => {
          console.log(`Support status updated for project with ID ${project.id}`);
        },
        error => {
          console.error('Error updating support status:', error);
          project.supported = !project.supported;
        }
      );
    } else {
      this.router.navigateByUrl('profile');
      this.notifier.notify('info', 'Please connect your ' + socialName + ' account first');
    }
  }

  setPage(i: number, event: any) {
    event.preventDefault();
    this.page = i;
    this.loadData(this.search, this.page, this.sort);
  }

  setSearch(event: any) {
    event.preventDefault();
    this.search = this.searchForm.get('search')?.value ?? "";
    this.loadData(this.search, this.page, this.sort);
  }

  setSort(event: any) {
    event.preventDefault();
    // this.sort = this.searchForm.get('sort')?.value ?? "default";
    this.sort = event.target.value;
    this.loadData(this.search, this.page, this.sort);
  }

  getSocialIconClass(name: string): string {
    switch (name) {
      case 'FACEBOOK':
        return 'fa fa-facebook';
      case 'X':
        return 'fa fa-twitter';
      case 'INSTAGRAM':
        return 'fa fa-instagram';
      default:
        return '';
    }
  }
}
