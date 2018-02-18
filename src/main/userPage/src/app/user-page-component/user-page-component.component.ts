import { Component, OnInit } from '@angular/core';
import { User } from "../user.model";
import {UserService} from "../user.service";
import {Http, Response} from '@angular/http';

@Component({
  selector: 'app-user-page-component',
  templateUrl: './user-page-component.component.html',
  providers: [UserService],
  styleUrls: ['./user-page-component.component.css']
})
export class UserPageComponentComponent implements OnInit {

  user: User= new User("","","","","","");

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getUser('ivanko@mail.ru')
      .subscribe(
        (user: any) => {
          this.user = user
        },
        (error) => console.log(error)
      );


  }
}
