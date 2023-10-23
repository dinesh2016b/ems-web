import { Authority } from "./authority";

export class User {
  id: number;
  username: string;
  authorities: Authority[];
  token: string;
}
