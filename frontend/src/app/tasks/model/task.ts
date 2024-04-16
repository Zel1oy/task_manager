import {Priority} from "./priority";
import {Status} from "./status";

export class Task {
   id!: number;
   name!: string;
   description!: string;
   priority!: Priority;
   status!: Status;
   dueDate!: Date;
}
