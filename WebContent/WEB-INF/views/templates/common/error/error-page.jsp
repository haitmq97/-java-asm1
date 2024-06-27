<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        .vh-100 {
            height: 100vh;
        }
        .error {
            color: red;
            font-size: 1.5rem;
        }
    </style>
</head>
<body>
    <div class="container vh-100 bg-light ">
        <div class="d-flex h-100 justify-content-center flex-column" style="position: relative; top: -20px">
            <div class="d-flex justify-content-center flex-column">
                <h1 class="display-1 font-weight-bold text-center">OOPS!</h1>
                <h3 class="text-center font-weight-bold">Xin lỗi! Có vẻ như đã có lỗi xẩy ra</h3>
                <p class="text-center d-flex align-items-center justify-content-center"><span class="error mr-2"><i class="fa-solid fa-circle-exclamation"></i></span> Vui lòng thử lại sau</p>
    
            </div>
            <div>
                <ul class="list-unstyled d-flex justify-content-center col-4 m-auto">
                    <li class="d-block p-3 col-6">
                        <a class="btn btn-primary btn-block" href="${pageContext.request.contextPath}/v1/home" role="button">Trang chủ</a>
                    </li>
                    <li class="d-block p-3 col-6">
                        <a class="btn btn-primary btn-block" href="#" role="button">Trở lại</a>
                    </li>
                </ul>
            </div>
        </div>

    </div>
    
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>