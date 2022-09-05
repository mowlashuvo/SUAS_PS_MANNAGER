package com.example.suas_ps_mannager;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceRequestModel implements Parcelable {
    private final String room_no;
    private final String contact_no;
    private final String description;
    private final String service_image;
    private final String status;
    private final String current_time;

    public ServiceRequestModel(String room_no, String contact_no, String description, String service_image, String status, String current_time) {
        this.room_no = room_no;
        this.contact_no = contact_no;
        this.description = description;
        this.service_image = service_image;
        this.status = status;
        this.current_time = current_time;
    }

    protected ServiceRequestModel(Parcel in) {
        room_no = in.readString();
        contact_no = in.readString();
        description = in.readString();
        service_image = in.readString();
        status = in.readString();
        current_time = in.readString();
    }

    public static final Creator<ServiceRequestModel> CREATOR = new Creator<ServiceRequestModel>() {
        @Override
        public ServiceRequestModel createFromParcel(Parcel in) {
            return new ServiceRequestModel(in);
        }

        @Override
        public ServiceRequestModel[] newArray(int size) {
            return new ServiceRequestModel[size];
        }
    };

    public String getRoom_no() {
        return room_no;
    }

    public String getContact_no() {
        return contact_no;
    }

    public String getDescription() {
        return description;
    }

    public String getService_image() {
        return service_image;
    }

    public String getStatus() {
        return status;
    }

    public String getCurrent_time() {
        return current_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(room_no);
        parcel.writeString(contact_no);
        parcel.writeString(description);
        parcel.writeString(service_image);
        parcel.writeString(status);
        parcel.writeString(current_time);
    }
}
