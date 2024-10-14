import { Role } from "./Role.type";

export interface User  {
    id?: number,
    name?: string,
    surname?: string,
    email?: string,
    confirmEmail?: string,
    password?: string,
    registerDate?: string,
    lastAccess?: string,
    roleId?: number,
    role?: Role,
    class?: string,
}