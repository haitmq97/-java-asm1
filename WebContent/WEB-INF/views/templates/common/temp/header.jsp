<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="<c:url value='/static/common/assets/css/header.css' />">
  <link rel="stylesheet" href="<c:url value='/static/common/assets/css/common-style.css' />">
  
<link rel="stylesheet"
	href="<c:url value='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css' />">

</head>
<body>

<main class="main">
        <div class="register-form form-container">
          <div class="form-head">
            <div class="form-title"><h3>Đăng ký</h3></div>
            <div class="form-head-decription"><p>Nhanh chóng, tiện lợi</p></div>
          </div>
          <div class="form-main">
            <form>
              <div class="f-row">
                <div class="f-field">
                  <div class="label-d">
                    <label class="field-label">
                      <span class="label-text">Họ và tên:</span>
                    </label>
                  </div>
                  <div class="input-d">
                    <div class="field-input">
                      <input type="text" class="input-p form-control" required/>
                    </div>
                    <div class=""></div>
                  </div>
                </div>

                <div class="f-field">
                  <div class="label-d">
                    <label class="field-label">
                      <span class="label-text">Email:</span>
                    </label>
                  </div>
                  <div class="input-d">
                    <div class="field-input">
                      <input type="email" class="input-p form-control" required/>
                    </div>
                    <div class=""></div>
                  </div>
                </div>
              </div>

              <div class="f-row">
                <div class="f-field">
                  <div class="label-d">
                    <label class="field-label">
                      <span class="label-text">Số điện thoại (tùy chọn):</span>
                    </label>
                  </div>
                  <div class="input-d">
                    <div class="field-input">
                      <input type="number" class="input-p form-control" />
                    </div>
                    <div class=""></div>
                  </div>
                </div>

                <div class="f-field">
                  <div class="label-d">
                    <label class="field-label">
                      <span class="label-text">Địa chỉ (tùy chọn):</span>
                    </label>
                  </div>
                  <div class="input-d">
                    <div class="field-input">
                      <input type="text" class="input-p form-control" />
                    </div>
                    <div class=""></div>
                  </div>
                </div>
              </div>

              <div class="f-row">
                <div class="f-field">
                  <div class="label-d">
                    <label class="field-label">
                      <span class="label-text">Tài khoản:</span>
                    </label>
                  </div>
                  <div class="input-d">
                    <div class="field-input">
                      <input type="text" class="input-p form-control" required/>
                    </div>
                    <div class=""></div>
                  </div>
                </div>

                <div class="f-field">
                  <div class="label-d">
                    <label class="field-label">
                      <span class="label-text">Mật khẩu:</span>
                    </label>
                  </div>
                  <div class="input-d">
                    <div class="field-input">
                      <input type="password" class="input-p form-control pw-field-custom-fix" required/>
                      <button class="button no-b-btn reveal-pass-btn" onclick="" title="Hiện mật khẩu">
                        <i class="fa-regular fa-eye"></i>
                      </button>
                    </div>
                    
                  </div>
                </div>
              </div>

              <div class="submit-p">
                <button class="cancel-btn ">Hủy</button>
                <button class="submit-btn">Đăng ký</button>
              </div>
              <div class="login-p"></div>
              <div class="term-p"></div>
            </form>
          </div>
          <div class="form-foot"></div>
        </div>
        <div></div>
      </main>



	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>