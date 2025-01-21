package com.example.frume.vo

import com.example.frume.model.DeliveryModel
import com.example.frume.util.DeliveryState
import com.example.frume.util.DeliverySubscribeState
import com.google.firebase.Timestamp

class DeliveryVO {
    // 배송 문서 ID
    var deliveryDocId = ""

    // 주문 ID
    var deliveryOrderDocId = ""

    // 배송지 문서 ID
    var deliveryAddressDocId = ""

    // 배송 방식
    var deliveryOption = ""

    // 배송 예정일
    var deliveryDueDate = Timestamp.now()

    // 정기배송여부
    var deliveryIsSubscribed = 0 // 0 : 비구독

    // 기타사항
    var deliveryEtc = ""

    // 등록시간
    var deliveryTimeStamp = Timestamp.now()

    // 배송 상태
    var deliveryState = 0 // 0 : 출고 준비중


    fun toDeliverModel(): DeliveryModel {
        val deliverModel = DeliveryModel()
        deliverModel.deliveryDocId = deliveryDocId
        deliverModel.deliveryOrderDocId = deliveryOrderDocId
        deliverModel.deliveryAddressDocId = deliveryAddressDocId
        deliverModel.deliveryOption = deliveryOption
        deliverModel.deliveryDueDate = deliveryDueDate
        deliverModel.deliveryEtc = deliveryEtc
        deliverModel.deliveryTimeStamp = deliveryTimeStamp

        when(deliveryIsSubscribed){
            DeliverySubscribeState.DELIVERY_STATE_SUBSCRIBE.num->{deliverModel.deliveryIsSubscribed=DeliverySubscribeState.DELIVERY_STATE_SUBSCRIBE}
            DeliverySubscribeState.DELIVERY_STATE_NOT_SUBSCRIBE.num->{deliverModel.deliveryIsSubscribed=DeliverySubscribeState.DELIVERY_STATE_NOT_SUBSCRIBE}
        }

        when(deliveryState){
            DeliveryState.DELIVERY_STATE_READY_FOR_SHIPMENT.num->{deliverModel.deliveryState=DeliveryState.DELIVERY_STATE_READY_FOR_SHIPMENT}
            DeliveryState.DELIVERY_STATE_SHIPMENT_COMPLETE.num->{deliverModel.deliveryState=DeliveryState.DELIVERY_STATE_SHIPMENT_COMPLETE}
            DeliveryState.DELIVERY_STATE_READY_FOR_DELIVERY.num->{deliverModel.deliveryState=DeliveryState.DELIVERY_STATE_READY_FOR_DELIVERY}
            DeliveryState.DELIVERY_STATE_IN_DELIVERY.num->{deliverModel.deliveryState=DeliveryState.DELIVERY_STATE_IN_DELIVERY}
            DeliveryState.DELIVERY_STATE_DELIVERED.num->{deliverModel.deliveryState=DeliveryState.DELIVERY_STATE_DELIVERED}

        }

        return deliverModel

    }
}