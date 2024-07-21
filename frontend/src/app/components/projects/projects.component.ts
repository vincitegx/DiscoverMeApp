import { AfterViewInit, Component, Input, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { BehaviorSubject, Observable, catchError, map, of, startWith } from 'rxjs';
import { AppState } from 'src/app/dtos/app-state';
import { Project } from 'src/app/dtos/project';
import { Socials } from 'src/app/dtos/socials';
import { DataState } from 'src/app/enums/data-state';
import { ProjectService } from 'src/app/services/project.service';
import { SocialService } from 'src/app/services/social.service';
import { environment } from 'src/environments/environment.development';
import { SharemodalComponent } from '../shared/sharemodal/sharemodal.component';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent {
  showShareModal: boolean = false;
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
    this.searchForm = new FormGroup({
      search: new FormControl('', [Validators.required]),
    })
    this.notifier = notifierService;
  }
  ngAfterViewInit(): void {
  }

  ngOnInit(): void {
    this.loadUserProjects(this.search, this.page);
    this.isProjectLimitReached$ = this.projectService.isProjectLimitExceeded().pipe(map(res => { return res }));
  }

  private loadUserProjects(search: string, page: number): void {
    this.appState$ = this.projectService.getUserProjects(search, page)
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
    this.loadUserProjects(this.search, this.page);
  }

  setSearch(event: any) {
    event.preventDefault();
    this.search = this.searchForm.get('search')?.value ?? "";
    this.loadUserProjects(this.search, this.page);
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

  onDeleteButtonClick(project: Project){}

  openShareModal(): void {
    this.showShareModal = true;
  }

  closeShareModal(): void {
    this.showShareModal = false;
  }

  convertCodeToShareLink(project: Project){
    return "https://localhost:4200"+project.url;
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
