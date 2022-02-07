import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { EditUnitHistory } from '../editunithistory';
import { Unit } from '../units';

@Injectable({
  providedIn: 'root'
})
export class UnitServiceService {

  private httpOptionPost = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token'),
      // Authorization: 'Basic ' + btoa('username:password'),
    }),
    responseType: 'text' as 'json'
  };

  private httpOptionGet = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token'),
      // Authorization: 'Basic ' + btoa('username:password'),
    }),
  };

  constructor(private httpCLient: HttpClient) { }

  public getAllUnits() {
    return this.httpCLient.get<any>("http://localhost:8080/getAllUnits", this.httpOptionGet)
      .pipe(catchError(this.handleError));
  }

  public addUnit(unit: Unit, fatherUnitId: number) {
    return this.httpCLient.post("http://localhost:8080/addUnit/" + fatherUnitId, unit, this.httpOptionPost)
      .pipe(catchError(this.handleError));
  }

  public deleteUnit(id: number) {
    return this.httpCLient.delete("http://localhost:8080/deleteUnit/" + id, this.httpOptionGet)
      .pipe(catchError(this.handleError));
  }

  public findUnitById(id: number) {
    return this.httpCLient.get<any>("http://localhost:8080/findUnitById/" + id, this.httpOptionGet)
      .pipe(catchError(this.handleError));
  }

  public editUnit(unitEdit: Unit, id: any, fatherUnitId: number) {
    return this.httpCLient.put("http://localhost:8080/editUnit/" + id + "/" + fatherUnitId, unitEdit, this.httpOptionPost)
      .pipe(catchError(this.handleError));
  }

  public getAllExcept1(id: number) {
    return this.httpCLient.get("http://localhost:8080/getAllUnitsExcept1/" + id, this.httpOptionGet)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` + `body was: ${error.error}`
      );
    }
    // return an observable with a user-facing error message
    return throwError('Something bad happened; please try again later.');
  }
}
