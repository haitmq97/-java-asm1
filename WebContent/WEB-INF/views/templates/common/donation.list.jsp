<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
      integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <link rel="stylesheet" href="table.css" />
    <title>Document</title>

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
  </head>
  <body>
    <div class="container">
      <div class="content">
        <div class="h-content">
          <div class="add-bar"><button class="btn btn-outline-success add-btn">Thêm mới</button></div>
          <div class="sp-tool d-flex flex-row justify-content-between mt-3">
            <div class="page-selector">
              <select class="entries-select rounded">
                <option>5</option>
                <option>10</option>
                <option>15</option>
                <option>20</option>
              </select>
              entries per page
            </div>
            <div class="search-box">
              <input class="rounded p-2 form-control" type="text" placeholder="Searching..." />
            </div>
          </div>
        </div>
        <div class="m-content">
          <table class="table table-striped table-content">
            <thead>
              <tr>
                <th scope="col"><p>Name</p></th>
                <th scope="col"><p>Start date</p></th>
                <th scope="col"><p>End date</p></th>
                <th scope="col"><p>Phone number</p></th>
                <th scope="col"><p>Action</p></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">
                  <p class="d-name">Hỗ trợ thiệt bị y tế cho trẻ em</p>
                  <p class="d-status">Đang quyên góp</p>
                </th>
                <td><p>2022-03-08</p></td>
                <td><p>2022-03-15</p></td>
                <td><p>023048920</p></td>
                <td class="action-c">
                  <button class="btn btn-success">Detail</button>
                  <button class="btn btn-success">Quyên góp</button>
                </td>
              </tr>

              <tr>
                <th scope="row">
                  <p class="d-name">Hỗ trợ lũ lụt miền Trung</p>
                  <p class="d-status">Kết thúc quyên góp</p>
                </th>
                <td><p>2022-03-08</p></td>
                <td><p>2022-03-15</p></td>
                <td><p>023048920</p></td>
                <td class="action-c">
                  <button class="btn btn-success">Detail</button>
                </td>
              </tr>
              <tr>
                <th scope="row">
                  <p class="d-name">Chung tay xây dựng Nhà Hạnh phúc</p>
                  <p class="d-status">Đang quyên góp</p>
                </th>
                <td><p>2022-03-08</p></td>
                <td><p>2022-03-15</p></td>
                <td><p>023048920</p></td>
                <td class="action-c">
                  <button class="btn btn-success">Detail</button>
                  <button class="btn btn-success">Quyên góp</button>
                </td>
              </tr>
              <tr>
                <th scope="row">
                  <p class="d-name">
                    Đồng hành cùng OneSky nâng cao chất lượng giáo dục
                  </p>
                  <p class="d-status">Đóng quyên góp</p>
                </th>
                <td><p>2022-03-08</p></td>
                <td><p>2022-03-15</p></td>
                <td><p>023048920</p></td>
                <td class="action-c">
                  <button class="btn btn-success">Detail</button>
                </td>
              </tr>
              <tr>
                <th scope="row">
                  <p class="d-name">Hỗ trợ thiệt bị y tế cho trẻ em</p>
                  <p class="d-status">Đóng quyên góp</p>
                </th>
                <td><p>2022-03-08</p></td>
                <td><p>2022-03-15</p></td>
                <td><p>023048920</p></td>
                <td class="action-c">
                  <button class="btn btn-success">Detail</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="f-content">
          <div class="page-number">
            <ul class="page-btn-list">
              <li ><button class="page-btn btn btn-outline-secondary">First</button></li>
              <li class="page-btn-item "><button class="page-btn btn btn-outline-secondary">Prev</button></li>
              <li class="page-btn-item">...</li>
              <li class="page-btn-item"><button class="page-btn btn btn-outline-secondary">3</button></li>
              <li class="page-btn-item"><button class="page-btn btn btn-outline-secondary">4</button></li>
              <li class="page-btn-item"><button class="page-btn btn btn-outline-secondary">5</button></li>
              <li class="page-btn-item">...</li>
              <li class="page-btn-item"><button class="page-btn btn btn-outline-secondary">Next</button></li>
              <li class="page-btn-item"><button class="page-btn btn btn-outline-secondary">Last</button></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
