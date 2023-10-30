import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appVideoHover]'
})
export class VideoHoverDirective {

  constructor(private el: ElementRef) { }

  @HostListener('mouseenter') onMouseEnter() {
    this.playVideo();
  }

  @HostListener('mouseleave') onMouseLeave() {
    this.pauseVideo();
  }

  private playVideo(): void {
    const video: HTMLVideoElement = this.el.nativeElement;
    if (video) {
      video.play();
    }
  }

  private pauseVideo(): void {
    const video: HTMLVideoElement = this.el.nativeElement;
    if (video) {
      video.pause();
      video.currentTime = 0;
    }
  }
}
