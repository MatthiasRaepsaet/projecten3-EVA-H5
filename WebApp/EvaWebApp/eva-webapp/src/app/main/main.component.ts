import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  /*public download(id: string, name: string) {
    this.loading.emit(true);
    this.downloadDocumentsService.getDocument(id).subscribe(data => {
      const blob = new Blob([data.blob()], {type: data.headers.get('content-type')});
      console.log(blob);
      console.log(data);
      console.log(data.headers.get('content-type'));
      FileSaver.saveAs(blob, this.getFileNameFromHttpResponse(data));
      this.loading.emit(false);
    });
    import { Injectable } from '@angular/core';
    import { Http, Response } from '@angular/http';
    import { Observable } from 'rxjs/Observable';
    import 'rxjs/add/operator/map';
    import { environment } from '../../../../environments/environment';
    import { RecentDocumentsDto } from './recentDocuments.dto';

    @Injectable()
    export class RecentDocumentsService {

      constructor(private http: Http) {
      }

      public getRecentDocuments(language: string): Observable<RecentDocumentsDto[]> {
        return this.http.get(${environment.privateApi}/document/recentdocuments?language= + language)
      .map((response: Response) => response.json())
          .map(res => res.recentDocumentDtoList);
      }

      public putRead(documentId) {
        return this.http.put(${environment.privateApi}/document/read/, documentId);
      }*/
}
