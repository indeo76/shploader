export interface StatusResponse {
  status: string;
  task: TaskResponse;
}

export interface TaskResponse {
  importMode: string;
  currentTaskDescription: string;
}
