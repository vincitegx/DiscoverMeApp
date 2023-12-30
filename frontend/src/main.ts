import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';


platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));

$(window).on('load', function () {
    $(".loader").fadeOut();
    $("#preloder").delay(100).fadeOut("slow");
});
