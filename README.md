# API Spec
### {{URL_LOCAL}} : localhost:8080/api
### {{URL_SERVER}} : http://35.247.142.238/api
### Swagger Link http://35.247.142.238/api/swagger-ui/index.html
### Workspace Collaboration Postman -> https://app.getpostman.com/join-team?invite_code=5e272200f6b1e7386f7828854fc84fe4&target_code=0b856c673d866291c61b04afac316d0a
## 1. Login dan Dashboard 
Request
- Method: POST
- Validation di MobileApp -> panjang string = 13 dan input = [0-9]
- Endpoint : `/api/v1/auth/login`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    "nik":"adminakun",
    "password":"123456"
}
```
Response
```
{
    "timestamp": "01-06-2022 21:26:33",
    "message": "Success!",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJwY_Zs",
        "username":"1871654852548",
        "password":"passwordAdmin",
        "firstName":"Jose",
        "lastName":"Mourinho",
        "birthDate": "1980-12-10",
        "gender":"Laki-laki",
        "email":"test@test.com",
        "noHandphone":"0721548484",
        "data" : {
          "id_health_facilities": 10,
          "name":"PUSKESMAS JATI"
        }
    }
}
```
### Dashboard Page
### Total Data Admin
Request
- Method: GET
- Endpoint : `/api/v1/dashboard/admin`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    
}
```
Response
```
{
    "timestamp": "01-06-2022 23:13:45",
    "message": "Success!",
    "data": {
        "id_admin": "200"
    }
}
```
### Total Data User
Request
- Method: GET
- Endpoint : `/api/v1/dashboard/user`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    
}
```
Response
```
{
    "timestamp": "01-06-2022 23:13:45",
    "message": "Success!",
    "data": {
        "id_user": "200"
    }
}
```
### Total Data Area (Kelurahan)
Request
- Method: GET
- Endpoint : `/api/v1/dashboard/area`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    
}
```
Response
```
{
    "timestamp": "01-06-2022 23:13:45",
    "message": "Success!",
    "data": {
        "id_area": "4"
    }
}
```
### Total Data RSUD
Request
- Method: GET
- Endpoint : `/api/v1/dashboard/category/rsud`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    
}
```
Response
```
{
    "timestamp": "01-06-2022 23:13:45",
    "message": "Success!",
    "data": {
        "id_category_facilities": "4"
    }
}
```
### Total Data Puskesmas
Request
- Method: GET
- Endpoint : `/api/v1/dashboard/category/puskesmas`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    
}
```
Response
```
{
    "timestamp": "01-06-2022 23:13:45",
    "message": "Success!",
    "data": {
        "id_category_facilities": "16"
    }
}
```
## 2. Kelola Booking dari User
### Get All data booking and set value 15 data
Request
- Method: GET
- Endpoint : `/api/v1/bookingpage?pageSize=15`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    
}
```
Response
```
{
    "timestamp": "01-06-2022 23:13:45",
    "message": "Success!",
    "data": {
        // show all data in table
    }
}
```
### Get data base on pagination and sorting
Request
- Method: GET
- Endpoint : `/api/v1/bookingpage?pageSize=15&pageNo=1&sortBy=name`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    
}
```
Response
```
{
    "timestamp": "01-06-2022 23:13:45",
    "message": "Success!",
    "data": {
        // show data defined
    }
}
```
### Get data booking by id
Request
- Method: GET
- Endpoint : `/api/v1/booking/{id}
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    
}
```
Response
```
{
    "timestamp": "01-06-2022 23:13:45",
    "message": "Success!",
    "data": {
        // show data defined
    }
}
```
## 3. Kelola Jadwal Vaksin dari Faskes
### Get All data Jadwal and set value 15 data
Request
- Method: GET
- Endpoint : `{{URL_SERVER}}/v1/session/0/2`
- Note : GET data pada halaman pertama (index 0) sebanyak 2 data
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    
}
```
Response
```
{
    "timestamp": "10-06-2022 09:29:28",
    "message": "Success!",
    "data": [
        {
            "id_session": 1,
            "stock": 100,
            "start_time": "07.00",
            "end_time": "10.00",
            "vaccine_dao": {
                "id_vaccine": 1,
                "vaccine_name": "SINOVAC"
            },
            "health_facilities_dao": {
                "created_at": [],
                "created_by": "SYSTEM",
                "updated_at": null,
                "is_deleted": false,
                "id_health_facilities": 1,
                "health_facilities_name": "Puskesmas Kota Karang",
                "address_health_facilities": "Komplek Kota Karang, Jl. Teluk Ratai.....",
                "link_location": "https://goo.gl/maps/uwRK8xgxBnAa9YgJ9",
                "phone_facilities": "(0721) 480129",
                "user_mapped": null,
                "category_mapped": {
                    "id_category_facilities": 1,
                    "category_facilities_name": "PUSKESMAS"
                },
                "area_mapped": {
                    "id_area": 1,
                    "area_name": "Kec. Kota Karang"
                }
            }
        }
    ]
}
```
### Tambahkan Jadwal vaksin
Request
- Method: POST
- Endpoint : `{{URL_SERVER}}/v1/session`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    "id_area": 1,
    "id_health_facilities": 1,
    "id_vaccine": 1,
    "stock": 50,
    "start_time": "13:00:00",
    "end_time":"15:00:00"
}
```
Response
```
{
    "timestamp": "16-06-2022 21:21:51",
    "message": "Success!",
    "data": {
        "id_session": 4,
        "stock": 30,
        "start_time": "07:00:00",
        "end_time": "11:00:00",
        "id_area": 1,
        "id_vaccine": 1,
        "id_health_facilities": 1
    }
}
```
### Edit Jadwal vaksin
Request
- Method: PUT
- Endpoint : `{{URL_SERVER}}/v1/session/{id}`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    "id_vaccine": 1,
    "stock": 30,
    "start_time": "07.00",
    "end_time":"10.00"
}
```
Response
```
{
    "timestamp": "16-06-2022 21:21:51",
    "message": "Success!",
    "data": {
        "id_session": 4,
        "stock": 30,
        "start_time": "07:00:00",
        "end_time": "11:00:00",
        "id_area": 1,
        "id_vaccine": 1,
        "id_health_facilities": 1
    }
}
```
### Delete schedule / data sesion vaccine by Id
Request
- Method: DELETE
- Endpoint : `{{URL_SERVER}}/v1/session/{id}`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    
}
```
Response
```
{
    "timestamp": "10-06-2022 09:31:58",
    "message": "Success!",
    "data": null
}
```
## 4. Kelola Berita
### GET All Data Berita
Request
- Method: GET
- Endpoint : `{{URL_LOCAL}}/v1/users/1`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
   
}
```
Response
```
{
    "timestamp": "10-06-2022 09:05:14",
    "message": "Success!",
    "data": {
        "created_at": "",
        "created_by": "SYSTEM",
        "updated_at": null,
        "is_deleted": false,
        "id_user": 1,
        "username": "adminpuskeskotakarang7",
        "password": "passwordpuskes",
        "first_name": "Septian Rahmat",
        "last_name": "KAKA",
        "birth_date": "1997-08-07",
        "gender": "Laki-Laki",
        "email": "test@test.com",
        "no_phone": "0724545874",
        "roles": "ADMIN",
        "image_profile": null,
        "health_facilities_dao_mapped": {
            "created_at": [ ],
            "created_by": "SYSTEM",
            "updated_at": null,
            "is_deleted": false,
            "id_health_facilities": 1,
            "health_facilities_name": "Puskesmas Kota Karang",
            "address_health_facilities": "Komplek Kota Karang, Jl. Teluk Ratai No. 18, Kota Karang, Teluk Betung Timur, Kota Karang, Kec. Telukbetung Timur, Kota Bandar Lampung, Lampung",
            "link_location": "https://goo.gl/maps/uwRK8xgxBnAa9YgJ9",
            "phone_facilities": "(0721) 480129",
            "user_mapped": null,
            "category_mapped": {
                "id_category_facilities": 1,
                "category_facilities_name": "PUSKESMAS"
            },
            "area_mapped": {
                "id_area": 1,
                "area_name": "Kec. Kota Karang"
            }
        }
    }
}
```
### Create New Data Schedule Vaccine
Request
- Method: POST
- Endpoint : `{{URL_LOCAL}}/v1/session`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    "id_health_facilities": 1,
    "id_vaccine": 1,
    "stock": 100,
    "start_time": "08.00",
    "end_time":"10.00"
}
```
Response
```
{
    "timestamp": "10-06-2022 09:11:04",
    "message": "Success!",
    "data": {
        "id_session": 1,
        "stock": 100,
        "start_time": "08.00",
        "end_time": "10.00",
        "id_vaccine": 1,
        "id_health_facilities": 1
    }
}
```
### Edit Data Schedule Vaccine
Request
- Method: PUT
- Endpoint : `{{URL_SERVER}}/v1/session/1`
- Note Confirm : when edit session, must disable field health_facilities and other atribute because not updated
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    "id_vaccine": 1,
    "stock": 30,
    "start_time": "07.00",
    "end_time":"10.00"
}
```
Response
```
{
    "timestamp": "10-06-2022 09:21:46",
    "message": "Success!",
    "data": {
        "id_session": 1,
        "stock": 100,
        "start_time": "07.00",
        "end_time": "10.00",
        "id_vaccine": 1,
        "id_health_facilities": 1
    }
}
```
## 6. Article News
### Get All Article News and set value 15 data
Request
- Method: GET
- Endpoint : `/api/v1/article?pageSize=15`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    
}
```
Response
```
{
    "timestamp": "01-06-2022 23:13:45",
    "message": "Success!",
    "data": {
        // show all data in table
    }
}
```
### Get data base on pagination and sorting
Request
- Method: GET
- Endpoint : `/api/v1/article?pageSize=15&pageNo=1&sortBy=name`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    
}
```
Response
```
{
    "timestamp": "01-06-2022 23:13:45",
    "message": "Success!",
    "data": {
        // show data defined
    }
}
```
### Delete article by Id
Request
- Method: DELETE
- Endpoint : `/api/v1/article/id`
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    
}
```
Response
```
{

}
```
## 7. Article News Page detail (2)
### Create New Data Article News
Request
- Method: POST
- Endpoint : `/api/v1/article
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    "title":"Pentingnya vaksin sebelum keluar rumah",
    "Author":"Admin",
    "iamge_aticle":"filedata",
    "body_content":"string unlimited",
}
```
Response
```
{
    "title":"Pentingnya vaksin sebelum keluar rumah",
    "Author":"Admin",
    "iamge_aticle":"filedata",
    "body_content":"string unlimited",
}
```
### Edit Data Schedule Vaccine
Request
- Method: PUT
- Endpoint : `/api/v1/article/id
- Header : 
  - Content-Type: application/json
  - Accept: application/json
- body
```
{
    "title":"Pentingnya vaksin sebelum keluar rumah",
    "Author":"Admin",
    "iamge_aticle":"filedata",
    "body_content":"string unlimited",
}
```
Response
```
{
    "title":"Pentingnya vaksin sebelum keluar rumah",
    "Author":"Admin",
    "iamge_aticle":"filedata",
    "body_content":"string unlimited",
}
```
