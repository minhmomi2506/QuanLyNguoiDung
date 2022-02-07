import { Unit } from "./units";

export class EditUserHistory{
    constructor(
        public userFullNameEdit:string,
        public userAddressEdit: string,
        public userDescriptionEdit:string,
        public userDateOfBirthEdit: Date,
        public unit: Unit,
    ){}

}