import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { BehaviorSubject, Observable, catchError, map, of, startWith, tap } from 'rxjs';
import { AppState } from 'src/app/dtos/app-state';
import { Project } from 'src/app/dtos/project';
import { DataState } from 'src/app/enums/data-state';
import { CalenderService } from 'src/app/services/calender.service';
import { ProjectService } from 'src/app/services/project.service';
import { environment } from 'src/environments/environment.development';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  private readonly apiServerUrl: string;
  readonly DataState = DataState;
  phase$: Observable<string> = of();
  pages: Array<number>= [];
  page:number=0;
  @Input() currentPage: number = 1;
  @Input() totalPages: number = 10;
  fileurl:string;
  projects$: Observable<Array<Project>>;
  appState$:Observable<AppState<any>> = of(); 
  search:string;
  searchForm: FormGroup;
  private dataSubject = new BehaviorSubject<Array<Project>>([]);
  constructor(private projectService: ProjectService, private calenderService: CalenderService) { 
    this.apiServerUrl = environment['api-base-url'];
    this.fileurl  = this.apiServerUrl+"api/v1/projects/contents";
    this.projects$  = of([]);
    this.search="";
    this.searchForm = new FormGroup({
      search : new FormControl('', [Validators.required]),
    })
  }

  ngOnInit(): void { 
    this.phase$ = this.calenderService.getCurrentCalender().pipe(map(response=>{
      return this.capitalizeFirstLetter(response.status)}),
      catchError((error:HttpErrorResponse)=>{
        console.log(error);
        return "";
      }));
      this.appState$ =this.projectService.getProjects(this.search,this.page)
      .pipe(
        map(response=>{
          this.dataSubject.next(response);
          this.pages = new Array(response['totalPages']);
          return {dataState:DataState.LOADED_STATE, appData:response}
        }),
        startWith({dataState:DataState.LOADING_STATE}),
        catchError((error:string)=>{
          return of({dataState:DataState.ERROR_STATE, error})
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

  onVoteButtonClick(project: Project): void {
    // Toggle the 'voted' property
    project.voted = !project.voted;

    // Send the updated value to the backend
    this.projectService.updateVoteStatus(project.id ?? 0).subscribe(
      () => {
        // Optional: Handle success (e.g., show a success message)
        console.log(`Vote status updated for project with ID ${project.id}`);
      },
      error => {
        // Optional: Handle error (e.g., show an error message)
        console.error('Error updating vote status:', error);
        // Revert the 'voted' property if the update fails
        project.voted = !project.voted;
      }
    );
  }

  setPage(i:number,event:any){
    event.preventDefault();
    this.page = i;
    this.appState$ = this.projectService.getProjects(this.search,this.page)
    .pipe(
      map(response=>{
        this.pages = new Array(response['totalPages']);
        return {dataState:DataState.LOADED_STATE, appData:response}
      }),
      startWith({dataState:DataState.LOADING_STATE}),
      catchError((error:string)=>{
        return of({dataState:DataState.ERROR_STATE, error})
      })
    );
  }

  getSocialIconClass(name: string): string {
    switch (name) {
      case 'FACEBOOK':
        return 'fa fa-facebook';
      case 'X':
        return 'fa fa-twitter';
      case 'INSTAGRAM':
        return 'fa fa-instagram';
      // Add more cases as needed
      default:
        return ''; // Default case, no icon
    }
  }

  capitalizeFirstLetter(word: string): string {
    if (word.length === 0) {
      return word;
    }
    const modifiedWord = word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
    return modifiedWord;
  }

  setSearch(event:any){
    event.preventDefault();
    this.search = this.searchForm.get('search')?.value ?? "";
    this.appState$ =this.projectService.getProjects(this.search,this.page)
      .pipe(
        tap(console.log),
        map(response=>{
          console.log(response);
          this.dataSubject.next(response);
          this.pages = new Array(response['totalPages']);
          console.log(this.pages);
          return {dataState:DataState.LOADED_STATE, appData:response}
        }),
        startWith({dataState:DataState.LOADING_STATE}),
        catchError((error:string)=>{
          return of({dataState:DataState.ERROR_STATE, error})
        })
      );
  }
}
