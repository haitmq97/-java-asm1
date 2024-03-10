function entriesChange() {

			var size = $("#pageSize").val();
			console.log(" page size: |" + size + "|");
			var page = 1;
			console.log(" current page: |" + page + "|");

			var searchingValue = document.getElementById("searching-input").value;
			console.log(" searching value: |" + searchingValue + "|");

			$.ajax({
				type : "GET",
				url : "<c:url value='/donation/list'> </c:url>",
				data : {
					size : size,
					page : page,
					searchingValue : searchingValue
				},
				success : function(data) {
					$("#donation-list").html(
							$(data).find("#donation-list").html());
				}
			});
		}
		function search(searchingValue) {

			console.log(" searching value: |" + searchingValue + "|");
			var size = $("#pageSize").val();
			$.ajax({
				type : "GET",
				url : "<c:url value='/donation/list'> </c:url>",
				data : {
					searchingValue : searchingValue,
					size : size
				},
				success : function(data) {
					$("#donation-list").html(
							$(data).find("#donation-list").html());
				}
			});
		}

		function changePage() {
			/* console.log(" searching value: |" + searchingValue + "|"); */
			var pageButtons = document.querySelectorAll(".page-btn");
			console.log(" number button: |" + pageButtons + "|");

			var searchingValue = document.getElementById("searching-input").value;

			console.log("searching value: " + searchingValue);
			/* pageButtons.forEach(function(button, index) {
			    console.log("Button " + (index + 1) + ":", button);
			}); */
			pageButtons.forEach(function(button) {
				button.addEventListener("click", function(event) {
					// Lấy thông tin về button được click và hiển thị trong console
					console.log("Button clicked:", button);
				});
			});

			/* pageButtons.forEach(function(button) {
			    button.addEventListener('click', function() {
			      // Lấy nội dung của button được click và hiển thị nó
			      var content = button.innerHTML;
			      console.log(content);
			    });
			  }); */

			/*  // Tạo một mảng để lưu trữ các promise từ các cuộc gọi AJAX
			 var ajaxPromises = [];

			 pageButtons.forEach(function(button) {
			     var page = button.textContent || button.innerText;
			     console.log("value: " +page);

			     // Tạo promise từ cuộc gọi AJAX và đẩy vào mảng
			     var ajaxPromise = $.ajax({
			         type: "GET",
			         url: "<c:url value='/donation/list'></c:url>",
			         data: {
			        searchingValue: searchingValue, 
			             page: page,
			             searchingValue: searchingValue
			         },
			         success: function(data) {
			             $("#donation-list").html($(data).find("#donation-list").html());
			         },
			         error: function(xhr, status, error) {
			             console.error("AJAX error:", error);
			         }
			     });

			     ajaxPromises.push(ajaxPromise);
			 });

			 // Sử dụng Promise.all để đợi tất cả các cuộc gọi AJAX hoàn thành
			 Promise.all(ajaxPromises)
			     .then(function() {
			         console.log("All AJAX requests completed successfully.");
			         // Thực hiện các hành động khác nếu cần
			     })
			     .catch(function(error) {
			         console.error("One or more AJAX requests failed:", error);
			     }); */
		}

		document.addEventListener("DOMContentLoaded", function() {
			var pageButtons = document.querySelectorAll(".page-btn");

			pageButtons.forEach(function(button) {
				button.addEventListener("click", function(event) {
					var size = $("#pageSize").val();
					console.log(" page size: |" + size + "|");
					var page = button.value;
					console.log(" change to page: |" + page + "|");

					var searchingValue = document
							.getElementById("searching-input").value;
					console.log(" searching value: |" + searchingValue + "|");

					$.ajax({
						type : "GET",
						url : "<c:url value='/donation/list'> </c:url>",
						data : {
							size : size,
							page : page,
							searchingValue : searchingValue
						},
						success : function(data) {
							$("#donation-list").html(
									$(data).find("#donation-list").html());
						}
					});
				});
			});
		});
		
		
		function redirectToDonateLink(donateLink) {

			var donateL = document.selectElementById("donatePopup");
			donateL.url=donateLink;
			openPopup('donate');
			
		}