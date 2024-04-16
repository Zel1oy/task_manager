import {Component, ViewChild} from '@angular/core';
import {NzColDirective, NzRowDirective} from "ng-zorro-antd/grid";
import {NzButtonComponent} from "ng-zorro-antd/button";
import {NzCheckboxComponent} from "ng-zorro-antd/checkbox";
import {NzInputDirective, NzInputGroupComponent} from "ng-zorro-antd/input";
import {FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {NzFormControlComponent, NzFormDirective} from "ng-zorro-antd/form";
import {NzIconDirective} from "ng-zorro-antd/icon";
import {NzTypographyComponent} from "ng-zorro-antd/typography";
import {NzFlexDirective} from "ng-zorro-antd/flex";
import {NzDividerComponent} from "ng-zorro-antd/divider";
import {NzTabComponent, NzTabLinkDirective, NzTabLinkTemplateDirective, NzTabSetComponent} from "ng-zorro-antd/tabs";
import {RouterLink} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    NzColDirective,
    NzButtonComponent,
    NzCheckboxComponent,
    NzInputDirective,
    NzRowDirective,
    NzInputGroupComponent,
    ReactiveFormsModule,
    NzFormDirective,
    NzFormControlComponent,
    NzIconDirective,
    NzTypographyComponent,
    NzFlexDirective,
    NzDividerComponent,
    NzTabSetComponent,
    NzTabComponent,
    RouterLink,
    NzTabLinkTemplateDirective,
    NzTabLinkDirective
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  baseUrl: string = 'http://localhost:8080/api/';
  @ViewChild('tabset') tabSet!: NzTabSetComponent;

  loginForm: FormGroup<{
    email: FormControl<string>;
    password: FormControl<string>;
  }> = this.fb.group({
    email: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });

  registerForm: FormGroup<{
    email: FormControl<string>;
    password: FormControl<string>;
    confirmPassword: FormControl<string>;
  }> = this.fb.group({
    email: ['', [Validators.required]],
    password: ['', [Validators.required]],
    confirmPassword: ['', [Validators.required]],
  });

  constructor(private fb: NonNullableFormBuilder, private httpClient: HttpClient) {
    if (localStorage.getItem('token')) {
      window.location.href = '/home';
    }
  }

  register() {
    if (this.registerForm.valid) {
      this.httpClient.post(this.baseUrl + 'auth/register', this.registerForm.value).subscribe(
        () => {
          this.loginForm.controls.email.setValue(this.registerForm.controls.email.value);
          this.loginForm.controls.password.setValue(this.registerForm.controls.password.value);
          this.login();
        }, (error) => {
          throw new Error('register failed', error);
      });
    } else {
      Object.values(this.registerForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }

  login() {
    console.log('enter login')
    if (this.loginForm.valid) {
      this.httpClient.post(this.baseUrl + 'auth/login', this.loginForm.value).subscribe((response: any) => {
        localStorage.setItem('token', response.token);
        if (localStorage.getItem('token')) {
          window.location.href = '/home';
        }
      }, (error) => {
        throw new Error('login failed', error);
      });
    } else {
      Object.values(this.loginForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }

  switchTab(tabIndex: number) {
    this.tabSet.nzSelectedIndex = tabIndex;
  }
}
