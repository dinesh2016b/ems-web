import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  }),
  withCredentials: true
};

@Injectable({ providedIn: 'root' })
export class BackendApiService {
  hostNameEndPointURL: String;

  constructor(private http: HttpClient) {
    this.hostNameEndPointURL = 'http://localhost:8080/ems-web-service';
  }

  //GET request
  public sendGetAPIRequest(url: string, tokenStr: String) {
    const httpOptions1 = {
      headers: new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + tokenStr
      }),
      withCredentials: true
    };

    return this.http.get<any>(this.hostNameEndPointURL + url, httpOptions1)
      .pipe(
        map((data: any) => data || {}),
        catchError(this.handleError)
      );
  }

  //POST request
  sendPostRequest(url: string, data: any ) {
    return this.http.post(this.hostNameEndPointURL + url, data, httpOptions)
      .pipe(
        map((data: any) => data || {}),
        catchError(this.handleError)
      );
  }

  //PUT request
  sendPutRequest(url: string, data: any) {
    return this.http.put(this.hostNameEndPointURL + url, data, httpOptions)
      .pipe(
        map((data: any) => data || {}),
        catchError(this.handleError)
      );
  }

  //DELETE request
  sendDeleteRequest(url: string, data: any) {
    return this.http.delete(this.hostNameEndPointURL + url, data)
      .pipe(
        map((data: any) => data || {}),
        catchError(this.handleError)
      );
  }

  //GET request
  sendGetAPIRequestWithParameter(url: string, inputParams: HttpParams) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      params: inputParams
    };
    return this.http.get(this.hostNameEndPointURL + url, httpOptions)
      .pipe(
        map((data: any) => data || {}),
        catchError(this.handleError)
      );
  }

  //Preflight/OPTIONS request used for CORS checks before authenticated calls.
  public sendOptionsRequest(url: string, data?: any) {
    const httpOptions = {
      body: data,
      headers: new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }),
      withCredentials: true
    };

    return this.http.request('OPTIONS', this.hostNameEndPointURL + url, httpOptions)
      .pipe(
        map((response: any) => response || {}),
        catchError(this.handleError)
      );
  }

  public sendPreflightRequest(url: string, data?: any) {
    return this.sendOptionsRequest(url, data);
  }

  //Error Handler
  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }
}