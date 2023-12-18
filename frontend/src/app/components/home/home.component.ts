import { Component, OnInit, Input } from '@angular/core';
import { Observable, map, of } from 'rxjs';
import { Project } from 'src/app/dtos/project';
import { ProjectService } from 'src/app/services/project.service';
import { environment } from 'src/environments/environment.development';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  private readonly apiServerUrl: string;
  @Input() currentPage: number = 1;
  @Input() totalPages: number = 10;
  fileurl:string;
  projects$: Observable<Array<Project>>;
  constructor(private projectService: ProjectService) { 
    this.apiServerUrl = environment['api-base-url'];
    this.fileurl  = this.apiServerUrl+"api/v1/projects/contents";
    this.projects$  = of([]);
  }

  ngOnInit(): void { 
    this.projects$ = this.projectService.getProjects().pipe(map(response=>{
      console.log(response.content)
      return response.content}));
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

  getSocialIconClass(name: string): string {
    switch (name) {
      case 'FACEBOOK':
        return 'fa fa-facebook';
      case 'TWITTER':
        return 'fa fa-twitter';
      case 'INSTAGRAM':
        return 'fa fa-instagram';
      // Add more cases as needed
      default:
        return ''; // Default case, no icon
    }
  }
}
