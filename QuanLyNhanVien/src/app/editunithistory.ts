import { Unit } from "./units";

export class EditUnitHistory{
    constructor(
        public unitIdEdit:string,
        public unitNameEdit: string,
        public unitDescriptionEdit:string,
        public unitFather: string,
    ){}

}