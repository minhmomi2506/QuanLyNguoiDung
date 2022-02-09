import { Injectable } from '@angular/core';
import {
    HttpEvent,
    HttpInterceptor,
    HttpHandler,
    HttpRequest,
    HttpHeaders,
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class Interceptor implements HttpInterceptor {
    constructor() { }

    intercept(
        request: HttpRequest<any>,
        next: HttpHandler
    ): Observable<HttpEvent<any>> {
        console.log('Interceptor');
        const token = String(localStorage.getItem('token'));
        const headers = new HttpHeaders()
            .set('Authorization', 'Bearer ' + token)
            .set('Content-Type', 'application/json');
        const AuthRequest = request.clone({ headers: headers });
        return next.handle(AuthRequest);
    }
}