import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, provideHttpClient, withFetch} from "@angular/common/http";
import {Task} from "../tasks/model/task";
import {TaskGroup} from "../tasks/model/task-group";

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private baseUrl: string = 'http://localhost:8080/api/';

  constructor(private httpClient: HttpClient) {}

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    });
  }

  getTaskGroups() {
    return this.httpClient.get<TaskGroup[]>(this.baseUrl + 'task_groups', {headers: this.getHeaders()});
  }

  getTasksForTaskGroup(taskGroupId: number) {
    return this.httpClient.get<Task[]>(this.baseUrl + 'task_groups/' + taskGroupId + '/tasks', {headers: this.getHeaders()});
  }
}
