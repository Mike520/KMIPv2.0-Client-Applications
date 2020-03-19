import { Component, OnInit } from '@angular/core';
import {HttpClientService, key} from '../service/http-client-service.service';

@Component({
  selector: 'app-create-key',
  templateUrl: './create-key.component.html',
  styleUrls: ['./create-key.component.css']
})
export class CreateKeyComponent implements OnInit {

  Key: key = new key("","","","");
  Key1: key = new key("","","","");
  constructor(
    private httpClientService: HttpClientService
  ) { }

  ngOnInit() {
  }

  createKey(){
    console.log(this.Key);

    this.httpClientService.createKey(this.Key)
        .subscribe( data => {
          this.Key1 = data;
          console.log(this.Key1);
        });
  }

}
