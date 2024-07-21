import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-sharemodal',
  templateUrl: './sharemodal.component.html',
  styleUrls: ['./sharemodal.component.css']
})
export class SharemodalComponent implements OnInit{
  ngOnInit(): void {
    console.log("Shared Modal")
  }
  showModal: boolean = false;

  @Output() close = new EventEmitter<void>();

  openModal() {
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
    this.close.emit();
  }

  copyLink() {
    // Logic to copy the link
    this.closeModal();
  }

  share(platform: string) {
    // Logic to share on the specified platform
    this.closeModal();
  }
}
