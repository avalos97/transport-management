import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CountryServiceService {

  private countries = [
    {id: 1, name: 'Colombia'},
    {id: 2, name: 'El Salvador'},
    {id: 3, name: 'Gueatemala'},
    {id: 4, name: 'Honduras'},
    {id: 5, name: 'Bolivia'}
  ];

  getCountries() {
    return this.countries;
  }
}
