import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Socials } from 'src/app/dtos/socials';
import { ProjectService } from 'src/app/services/project.service';
import { ProjectRequest } from './project-request';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { AuthService } from 'src/app/services/auth.service';
import { SocialService } from 'src/app/services/social.service';
import { BehaviorSubject, catchError, finalize, map, of, tap } from 'rxjs';

@Component({
  selector: 'app-submit-project',
  templateUrl: './submit-project.component.html',
  styleUrls: ['./submit-project.component.css']
})
export class SubmitProjectComponent implements OnInit {
  public socials: Array<Socials> = [];
  public video: any = File;
  addProjectForm: FormGroup;
  projectRequest: ProjectRequest;
  public defaultSelectedPlatform: number = 1;
  isProjectLimitReached:BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private readonly notifier: NotifierService;
  constructor(private authService: AuthService, private router: Router,
    private projectService: ProjectService, notifierService: NotifierService,
    private socialService: SocialService) {
    this.addProjectForm = new FormGroup({
      title: new FormControl('', Validators.required),
      link: new FormControl('', Validators.required),
      file: new FormControl('', Validators.required),
      social: new FormControl('', [Validators.required]),
    });
    this.projectRequest = new ProjectRequest('', '', new Socials());
    this.notifier = notifierService;
  }
  ngOnInit(): void {
    this.getPlatforms();
    this.projectService.isProjectLimitExceeded().pipe(
      map(resp=>{
      this.isProjectLimitReached.next(resp);
    }),
    catchError(error => {
      console.error('Error fetching project limit:', error);
      return of(false);
    }),
    finalize(() => {
    })
    ).subscribe();
  }

  public saveProject(): void {
    if (this.isProjectLimitReached.value) {
      this.notifier.notify('error', 'Project Submission Limit Reached');
    } else {
      this.projectRequest.setSongTitle(this.addProjectForm.get('title')?.value);
      this.projectRequest.setSongUri(this.addProjectForm.get('link')?.value);
      this.projectRequest.setSocial({ id: this.addProjectForm.get('social')?.value });
      const selectedSocialId = 1;
      if (selectedSocialId === 1) { // Assuming 1 is the ID for FACEBOOK
        // Use AuthService to check if the Facebook account is connected
        if (!this.socialService.isSocialConnected('FACEBOOK')) {
          console.error('You have to connect the Facebook account first.');
          this.notifier.notify('error', 'You have to connect the Facebook account first.');
          return;
        }
      }
      const formData = new FormData();
      formData.append('request', JSON.stringify(this.projectRequest));
      formData.append('content', this.video);
      this.projectService.addProject(formData).subscribe(
        (response) => {
          this.router.navigateByUrl('link');
        }
      );
    }
  }

  onSelectVideo(e: any) {
    const videoFile = e.target.files[0];
    // const maxSizeInBytes = 10 * 1024 * 1024; // 10 MB
    // if (!videoFile.type.startsWith('video/')) {
    //   console.error('Invalid file type. Please select a video file.');
    //   this.notifier.notify('error', 'Invalid file type. Please select a video file.');
    //   return;
    // }
    // if (videoFile.size > maxSizeInBytes) {
    //   console.error('File size should be no greater than 10 MB.');
    //   this.notifier.notify('error', 'File size should be no greater than 10 MB.');
    //   return;
    // }
    // const reader = new FileReader();
    // reader.onload = (event: any) => {
    //   const videoElement = document.createElement('video');
    //   videoElement.src = event.target.result;
    //   videoElement.addEventListener('loadedmetadata', () => {
    //     const durationInSeconds = Math.floor(videoElement.duration);
    //     if (durationInSeconds > 60) {
    //       console.error('Video duration should be no longer than 60 seconds.');
    //       this.notifier.notify('error', 'Video duration should be no longer than 60 seconds.');
    //       return;
    //     }
    //     const sourceRatio = videoElement.videoWidth / videoElement.videoHeight;
    //     if (sourceRatio < 1.91 || sourceRatio > 9 / 16) {
    //       console.error('Invalid source ratio. It should be between 1.91:1 and 9:16.');
    //       this.notifier.notify('error', 'Invalid source ratio. It should be between 1.91:1 and 9:16.');
    //       return;
    //     }
    //     this.video = videoFile;
    //   });
    //   videoElement.load();
    // };
    // reader.readAsDataURL(videoFile);
    this.video = videoFile;
  }

  public getPlatforms(): Array<Socials> {
    this.socialService.getAllSocials().subscribe(
      (data) => {
        this.socials = data;
        return data;
      }, (error: HttpErrorResponse) => {
        this.socials = this.staticPlatformData();
      }
    );
    return this.socials;
  }

  private staticPlatformData(): Array<Socials> {
    return [
      {
        "id": 1,
        "name": "FACEBOOK"
      },
      {
        "id": 2,
        "name": "X"
      },
      {
        "id": 3,
        "name": "TIKTOK"
      },
      {
        "id": 4,
        "name": "INSTAGRAM"
      }
    ];
  }
}
