import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';


platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));

$(window).on('load', function () {
    $(".loader").fadeOut();
    $("#preloder").delay(150).fadeOut("slow");
});
