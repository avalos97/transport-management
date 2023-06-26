import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Shipment } from '../interface/shipment';
import { Observable } from 'rxjs';
import { ShippingType } from '../interface/shipping-type';

@Injectable({
  providedIn: 'root'
})
export class ShipmentService {

  apiUrl = environment.REST_API_URL + 'shipments';

  constructor(private http: HttpClient) { }

  getAllShipments(): Observable<Shipment[]> {
    return this.http.get<Shipment[]>(this.apiUrl);
  }

  getShipmentById(id: number): Observable<Shipment> {
    return this.http.get<Shipment>(`${this.apiUrl}/${id}`);
  }

  addShipment(shipment: Shipment): Observable<Shipment> {
    return this.http.post<Shipment>(this.apiUrl, shipment);
  }

  updateShipment(shipment: Shipment): Observable<Shipment> {
    return this.http.put<Shipment>(`${this.apiUrl}/${shipment.id}`, shipment);
  }

  getAllShippingType(): Observable<ShippingType[]> {
    return this.http.get<ShippingType[]>(this.apiUrl + '/types');
  }

  delete(id: number): Observable<Shipment> {
    return this.http.delete<Shipment>(`${this.apiUrl}/${id}`);
  }
}
