<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Register</title>
    
    
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
      integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
      rel="stylesheet"
    />

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
      crossorigin="anonymous"
    />

    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>

    <script
      src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
      crossorigin="anonymous"
    ></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    

    
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">


<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/register-style.css' />">

<script src="<c:url value='/static/common/assets/js/form.js' />"></script>
</head>
<body>

 <main class="main container">
        <div class="register-form form-container">
          <div class="form-head">
            <div class="form-title"><h3>Đăng ký</h3></div>
            <div class="form-head-decription"><p>Nhanh chóng, tiện lợi</p></div>
          </div>
          <div class="form-main">
            <form:form action="${process}" method="POST" modelAttribute="user">
              <div class="f-row">
                <div class="f-field">
                  <div class="label-d">
                    <label class="field-label">
                      <span class="label-text">Họ và tên:</span>
                    </label>
                  </div>
                  <div class="input-d">
                    <div class="field-input">
                      <form:input type="text" class="input-p form-control" path="fullName" />
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
                      <form:input type="email" class="input-p form-control" path="email" />
                      
                      <form:errors path="email" />
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
                      <form:input type="number" class="input-p form-control" path="phoneNumber"/>
                      <form:errors path="phoneNumber" />
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
                      <form:input type="text" class="input-p form-control" path="address"/>
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
                      <form:input type="text" class="input-p form-control" path="userName" autocomplete="off"/>
                      
                      <form:errors path="userName" />
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
                      <form:input type="password" id="passwordInput" class="input-p form-control pw-field-custom-fix" path="password" autocomplete="new-password"/>
                      <button type="button" class="button no-b-btn toggle-pass-btn" onclick="togglePassword()" title="Hiện mật khẩu">
                        <i id="eyeIcon" class="fa-regular fa-eye"></i>
                      </button>
                      
                      <form:errors path="password" />
                    </div>

                 
                  </div>
                </div>
              </div>
              <div class="d-flex justify-content-center align-items-center">
							<p class="term-in" style="display: inline;">
								Bằng cách nhấp vào Đăng ký, bạn đồng ý với <a href="">Điều
									khoản, Chính sách quyền riêng tư và Chính sách cookie</a> của chúng
								tôi.
							</p>

						</div>

              <div class="submit-p">
                <button type="button" class="cancel-btn " onclick="window.location.href='${pageContext.request.contextPath}/v1/home'">Hủy</button>
                <button type="submit" class="submit-btn">Đăng ký</button>
              </div>
              <div class="login-p"></div>
              <div class="term-p"></div>
            </form:form>
          </div>
          <div class="form-foot"></div>
        </div>
        <div></div>
      </main>
<script src="<c:url value='/static/common/assets/js/form.js' />"></script>


</body>
</html>