import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

export class user{
  constructor(
    public userName: string,
    public password: string
  ){}
}

export class key{
  constructor(
    public typeOfKey: string,
    public algorithm: string,
    public keySize: string,
    public uniqueIdentifier: string,
  ){}
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

    constructor(private httpClient:HttpClient
      ) { 
  }

  getUsers(){
    return this.httpClient.get<user[]>('http://localhost:8080/users')
  }

  public createKey(key) {
    return this.httpClient.post<key>("http://localhost:8080/createkey", key);
  }

  public getKey(key) {
    return this.httpClient.post("http://localhost:8080/getkey", key, {
      responseType: 'text' 
   });
  }

  public destroyKey(key) {
    return this.httpClient.post("http://localhost:8080/destroykey", key,{
      responseType: 'text' 
   });
  }

  public encrypt(uploadFile: FormData){
    return this.httpClient.post("http://localhost:8080/encrypt", uploadFile, {
      responseType: 'text' 
   });
  }
}
