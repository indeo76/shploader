
export abstract class AbstractShpComponent<Data> {
  getTyped(data: any): Data {
    return data;
  }
}
