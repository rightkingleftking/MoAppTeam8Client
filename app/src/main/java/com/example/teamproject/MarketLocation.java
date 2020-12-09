package com.example.teamproject;

import com.google.android.gms.maps.model.LatLng;

public class MarketLocation {
    public static final LatLng[] superLocation = {
            new LatLng(35.8663, 128.5917), // 동아쇼핑
            new LatLng(35.8605, 128.5637), // 홈플러스 내당점
            new LatLng(35.8688, 128.6727), // 롯데마트 율하점
            new LatLng(35.8460, 128.6120), // 롯데슈퍼 수성점
            new LatLng(35.8995, 128.6103), // 북대구농협 하나로마트
            new LatLng(35.8711, 128.6370), // 이마트 만촌점
            new LatLng(35.8494, 128.5273), // 홈플러스 성서점
            new LatLng(35.8850, 128.5897), // 이마트 칠성점
    };

    public static final LatLng[] tradiLocation = {
            new LatLng(35.8611, 128.5920), // 남문시장
            new LatLng(35.8744, 128.6399), // 동구시장
            new LatLng(35.8688, 128.5808), // 서문시장
            new LatLng(35.8453, 128.6035), // 봉덕시장
            new LatLng(35.8766, 128.6046), // 칠성시장
            new LatLng(35.8556, 128.6188), // 수성시장
            new LatLng(35.8537, 128.5458), // 서남시장
            new LatLng(35.8892, 128.5704), // 팔달시장
    };

    public static final String[] superName = {
            "동아쇼핑",
            "홈플러스 내당점",
            "롯데마트 율하점",
            "롯데슈퍼 수성점",
            "북대구농협 하나로마트",
            "이마트 만촌점",
            "홈플러스 성서점",
            "이마트 칠성점"
    };

    public static final String[] tradiName = {
            "남문시장",
            "동구시장",
            "서문시장",
            "봉덕시장",
            "칠성시장",
            "수성시장",
            "서남시장",
            "팔달시장"
    };

    // return nearest index
    public static int nearSuper(LatLng currentLocation) {
        int ret = 0;
        double distance = 0.0;

        for(int i = 0; i < superLocation.length; i++) {
            double t = (superLocation[i].latitude - currentLocation.latitude) * (superLocation[i].latitude - currentLocation.latitude)
                    + (superLocation[i].longitude - currentLocation.longitude) * (superLocation[i].longitude - currentLocation.longitude);
            if(i == 0 || distance > t) {
                ret = i;
                distance = t;
            }
        }
        return ret;
    }

    // return nearest index
    public static int nearTradi(LatLng currentLocation) {
        int ret = 0;
        double distance = 0.0;

        for(int i = 0; i < tradiLocation.length; i++) {
            double t = (tradiLocation[i].latitude - currentLocation.latitude) * (tradiLocation[i].latitude - currentLocation.latitude)
                    + (tradiLocation[i].longitude - currentLocation.longitude) * (tradiLocation[i].longitude - currentLocation.longitude);
            if(i == 0 || distance > t) {
                ret = i;
                distance = t;
            }
        }
        return ret;
    }
}