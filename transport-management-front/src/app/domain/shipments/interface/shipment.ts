export class Shipment {

    id?: number;
    quantity?: number;
    registrationDate?: Date;
    deliveryDate?: Date;
    shippingPrice?: number;
    discount?: number;
    fleetVehicleNumber?: string;
    guideNumber?: string;
    isActive?: boolean;

    productId?: number;
    clientId?: number;
    storageId?: number;
    shippingTypeId?: number;
    
    productName?: string;
    clientName?: string;
    storageName?: string;
    shippingTypeName?: string;
}
