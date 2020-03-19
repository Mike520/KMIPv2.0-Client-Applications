import { Component, OnInit } from '@angular/core';
import {HttpClientService, key} from '../service/http-client-service.service';

@Component({
  selector: 'app-get-key',
  templateUrl: './get-key.component.html',
  styleUrls: ['./get-key.component.css']
})
export class GetKeyComponent implements OnInit {

  Key: key = new key("","","","");
  Key1: string;
  constructor(
    private httpClientService: HttpClientService
  ) { }

  ngOnInit() {
  }

  getKey(){
    console.log("Get Key clicked");
    console.log(this.Key);
    this.httpClientService.getKey(this.Key)
        .subscribe( data => {
          this.Key1 = data;
          console.log(this.Key1);
        });
  }

}
