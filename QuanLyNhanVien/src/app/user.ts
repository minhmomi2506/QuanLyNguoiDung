import { Role } from "./roles";
import { Unit } from "./units";

export class User{
    constructor(
        public username:string,
        public password: string,
        public fullName: string,
        public dateOfBirth: Date,
        public description:string,
        public address:string,
        public unit: Unit
    ){}

}