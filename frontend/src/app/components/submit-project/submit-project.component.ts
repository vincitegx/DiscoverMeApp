import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Socials } from 'src/app/dtos/socials';
import { ProjectService } from 'src/app/services/project.service';
import { ProjectRequest } from './project-request';
import { Router } from '@angular/router';
import { CalenderService } from 'src/app/services/calender.service';

@Component({
  selector: 'app-submit-project',
  templateUrl: './submit-project.component.html',
  styleUrls: ['./submit-project.component.css']
})
export class SubmitProjectComponent implements OnInit{
  public socials: Array<Socials> = [];
  public video: any = File;
  addProjectForm: FormGroup;
  projectRequest: ProjectRequest;
  public defaultSelectedPlatform: number=1;
  constructor(private router: Router, private projectService: ProjectService, private calenderService: CalenderService){
    this.addProjectForm = new FormGroup({
      title : new FormControl('', Validators.required),
      link : new FormControl('', Validators.required),
      file : new FormControl('', Validators.required),
      social : new FormControl('', [Validators.required]),
    });
    this.projectRequest = new ProjectRequest('','', new Socials());
  }
  ngOnInit(){
    this.getPlatforms();
  }

  public saveProject(): void {
    this.projectRequest.setSongTitle(this.addProjectForm.get('title')?.value);
    this.projectRequest.setSongUri(this.addProjectForm.get('link')?.value);
    this.projectRequest.setSocial({id:this.addProjectForm.get('social')?.value});
    const formData = new FormData();
    formData.append('request', JSON.stringify(this.projectRequest));
    formData.append('content', this.video);
    this.projectService.addProject(formData).subscribe(
        (response)=>{
          this.router.navigateByUrl('home');
        }
      );
  }

  onSelectVideo(e:any){
    const video = e.target.files[0];
    this.video = video;
  }

  public getPlatforms():Array<Socials>{
    this.projectService.getAllSocials().subscribe(
      (data)=>{
        this.socials = data;
        return data;
      },(error: HttpErrorResponse)=>{
        this.socials= this.staticPlatformData();
      }
    );
    return this.socials;
  }

  private staticPlatformData():Array<Socials>{
    return [
      {
        "id":1,
        "name": "fACEBOOK"
      },
      {
        "id":2,
        "name": "X"
      },
      {
        "id":3,
        "name": "TIKTOK"
      },
      {
        "id":4,
        "name": "INSTAGRAM"
      },
      {
        "id":5,
        "name": "YOUTUBE"
      }
    ];
  }
}
