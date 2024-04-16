import {Component} from '@angular/core';
import {NzContentComponent, NzHeaderComponent, NzLayoutComponent, NzSiderComponent} from "ng-zorro-antd/layout";
import {NzMenuDirective, NzMenuItemComponent, NzSubMenuComponent} from "ng-zorro-antd/menu";
import {NzIconDirective} from "ng-zorro-antd/icon";
import {TaskGroup} from "./model/task-group";
import {DatePipe, NgForOf} from "@angular/common";
import {
  NzTableComponent,
  NzTdAddOnComponent,
  NzThAddOnComponent,
  NzThMeasureDirective,
  NzThSelectionComponent
} from "ng-zorro-antd/table";
import {Task} from "./model/task";
import {Status} from "./model/status";
import {Priority} from "./model/priority";
import {TaskService} from "../services/task.service";

@Component({
  selector: 'app-tasks',
  standalone: true,
  imports: [
    NzLayoutComponent,
    NzHeaderComponent,
    NzMenuDirective,
    NzMenuItemComponent,
    NzIconDirective,
    NzSiderComponent,
    NzSubMenuComponent,
    NzContentComponent,
    NgForOf,
    NzTableComponent,
    NzThSelectionComponent,
    NzTdAddOnComponent,
    DatePipe,
    NzThAddOnComponent,
    NzThMeasureDirective
  ],
  templateUrl: './tasks.component.html',
  styleUrl: './tasks.component.scss'
})
export class TasksComponent {
  checked = false;
  indeterminate = false;
  isCollapsed = false;
  allTaskGroups: TaskGroup[] = [];
  currentListOfTasks: Task[] = [];
  listOfCurrentPageData: readonly Task[] = [];
  setOfCheckedId = new Set<number>();

  constructor(private taskService: TaskService) {
    this.allTaskGroups = [
      {
        id: 1,
        name: 'Group 1',
        tasks: [
          {
            id: 1,
            name: 'Task 1',
            description: 'Description 1',
            status: Status.IN_PROGRESS,
            priority: Priority.MEDIUM,
            dueDate: new Date()
          },
          {
            id: 2,
            name: 'Task 2',
            description: 'Description 2',
            status: Status.IN_PROGRESS,
            priority: Priority.MEDIUM,
            dueDate: new Date()
          }
        ]
      },
      {
        id: 2,
        name: 'Group 2',
        tasks: [
          {
            id: 3,
            name: 'Task 3',
            description: 'Description 3',
            status: Status.IN_PROGRESS,
            priority: Priority.LOW,
            dueDate: new Date()
          },
          {
            id: 4,
            name: 'Task 4',
            description: 'Description 4',
            status: Status.IN_PROGRESS,
            priority: Priority.HIGH,
            dueDate: new Date()
          }
        ]
      }
    ];
  }

  selectTaskGroup(taskGroup: TaskGroup) {
    if (!taskGroup.tasks) {
      console.log('fetching http')
      this.taskService.getTasksForTaskGroup(taskGroup.id).subscribe(tasks => {
        taskGroup.tasks = tasks;
        this.currentListOfTasks = tasks;
      });
    } else {
      console.log('using cached data')
      this.currentListOfTasks = taskGroup.tasks;
    }
  }

  onCurrentPageDataChange($event: readonly Task[]): void {
    this.listOfCurrentPageData = $event;
    this.refreshCheckedStatus();
  }

  onAllChecked(value: boolean): void {
    this.listOfCurrentPageData.forEach(item => this.updateCheckedSet(item.id, value));
    this.refreshCheckedStatus();
  }

  onItemChecked(id: number, checked: boolean): void {
    this.updateCheckedSet(id, checked);
    this.refreshCheckedStatus();
  }

  refreshCheckedStatus(): void {
    this.checked = this.listOfCurrentPageData.every(item => this.setOfCheckedId.has(item.id));
    this.indeterminate = this.listOfCurrentPageData.some(item => this.setOfCheckedId.has(item.id)) && !this.checked;
  }

  updateCheckedSet(id: number, checked: boolean): void {
    if (checked) {
      this.setOfCheckedId.add(id);
    } else {
      this.setOfCheckedId.delete(id);
    }
  }

  priorityCompare() {
    return (a: Task, b: Task) => a.priority - b.priority;
  }

  statusCompare() {
    return (a: Task, b: Task) => a.status - b.status;
  }

  dueDateCompare() {
    return (a: Task, b: Task) => a.dueDate.getTime() - b.dueDate.getTime();
  }
}
