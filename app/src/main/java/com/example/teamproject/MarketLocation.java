package com.example.teamproject;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

public class MarketLocation {
    static LatLng cLocation;
    public static final LatLng[] superLocation = {
            new LatLng(35.8663, 128.5917), // 동아쇼핑
            new LatLng(35.86078, 128.56378), // 홈플러스 내당점
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

    // return nearest market "p1~8"
    public static String nearSuper(LatLng currentLocation) {
        int ret = 0;
        double distance = 0.0;

        for(int i = 0; i < superLocation.length; i++) {
            double t = calcSuperDistance(currentLocation, i);
            if(i == 0 || distance > t) {
                ret = i;
                distance = t;
            }
        }
        return "p" + (ret + 1);
    }

    // return nearest market "p1~8"
    public static String nearTradi(LatLng currentLocation) {
        int ret = 0;
        double distance = 0.0;

        for(int i = 0; i < tradiLocation.length; i++) {
            double t = calcTradiDistance(currentLocation, i);
            if(i == 0 || distance > t) {
                ret = i;
                distance = t;
            }
        }
        return "p" + (ret + 1);
    }

    // return float meter
    public static float calcDistance(LatLng location1, LatLng location2) {  //
        Location l1 = new Location(LocationManager.GPS_PROVIDER);
        l1.setLatitude(location1.latitude);
        l1.setLongitude(location1.longitude);
        Location l2 = new Location(LocationManager.GPS_PROVIDER);
        l2.setLatitude(location2.latitude);
        l2.setLongitude(location2.longitude);

        return l1.distanceTo(l2);
    }

    public static float calcTradiDistance(LatLng currentLocation, int index) {
        return calcDistance(currentLocation, tradiLocation[index]);
    }

    public static float calcSuperDistance(LatLng currentLocation, int index) {
        return calcDistance(currentLocation, superLocation[index]);
    }
}