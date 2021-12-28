import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { EditUnitHistory } from './editunithistory';
import { Unit } from './units';

@Injectable({
  providedIn: 'root'
})
export class UnitServiceService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      // Authorization: 'my-auth-token',
      // Authorization: 'Basic ' + btoa('username:password'),
    }),
  };

  constructor(private http:HttpClient) { }

  public getAllUnits(){
    return this.http.get<any>("http://localhost:8080/getAllUnits", this.httpOptions)
    .pipe(catchError(this.handleError));
  }

  public addUnit(unit: Unit,fatherUnitId:number){
    return this.http.post("http://localhost:8080/addUnit/"+fatherUnitId,unit, { responseType: 'text' as 'json' });
  }

  public deleteUnit(id:number){
    return this.http.delete("http://localhost:8080/deleteUnit/"+id);
  }

  public findUnitById(id: number){
    return this.http.get<any>("http://localhost:8080/findUnitById/" +id, this.httpOptions)
    .pipe(catchError(this.handleError));
  }

  public editUnit(editUnitHistory:EditUnitHistory, id: any){
    return this.http.put("http://localhost:8080/editUnit/"+id,editUnitHistory, { responseType: 'text' as 'json' });
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
