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
    <title>Login - SB Admin</title>
    
    
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

    
    <link rel="stylesheet" href="headerforRegister.css" />

    <link rel="stylesheet" href="footer.css" />
    <link rel="stylesheet" href="register.css" />
    
    
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<script
	src="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js' />"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/register-form.css' />">

<script src="<c:url value='/static/common/assets/js/form.js' />"></script>
</head>
<body>








	<div class="full-form-container">
		<div id="overlay" onclick="closeAllPopup()"></div>
		<div class="popup  col-6">
			<div class="register-form">
				<form:form action="${process}" method="POST" modelAttribute="user">
					<div class="h-form">
						<h3 class="form-title">Đăng ký</h3>
					</div>
					<div class="m-form">
						<div class="row custom-row">
							<div class="col-6">
								<label for="name" class="i-label"><span>Họ và
										tên:</span></label>
								<form:input id="name" type="text" path="fullName"
									class="input-field form-control" />
							</div>

							<div class="col-6">
								<label for="email" class="i-label"><span>Email:</span></label>
								<form:input id="email" type="text" path="email"
									class="input-field form-control" />
							</div>
						</div>

						<div class="row custom-row">
							<div class="col-6">
								<label for="phoneNumber" class="i-label"><span>Số
										điện thoại:</span></label>
								<form:input id="phoneNumber" type="text" path="phoneNumber"
									class="input-field form-control" />
							</div>

							<div class="col-6">
								<label for="address" class="i-label"><span>Địa chỉ:</spa></label>
								<form:input id="address" type="text" path="address"
									class="input-field form-control" />
							</div>
						</div>
						<div class="row custom-row">
							<div class="col-6">
								<label for="userName" class="i-label"><span>Tài
										khoản:</span></label>
								<form:input id="userName" type="text" path="userName"
									class="input-field form-control" />
							</div>
							<div class="col-6">
								<label for="password" class="i-label"><span>Mật
										khẩu:</span></label>
								<form:input id="password" type="password" path="password"
									class="input-field form-control" />
							</div>
						</div>
						<div class="d-flex justify-content-center align-items-center">
							<p class="term-in" style="display: inline;">
								Bằng cách nhấp vào Đăng ký, bạn đồng ý với <a href="">Điều
									khoản, Chính sách quyền riêng tư và Chính sách cookie</a> của chúng
								tôi.
							</p>

						</div>
					</div>
					<div class="f-form d-flex flex-row">
						<button class="close-btn btn btn-secondary" onclick="">
							Đóng</button>
						<button type="submit" class="submit-btn submit-custom-btn">
							Đăng ký</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	
	<hr>
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








</body>
</html>