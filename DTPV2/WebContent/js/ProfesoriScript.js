
function getAllProfs(callbackFunction){
	$.ajax({
		url: "rest/profs/get-all-prof-names",
		dataType: "application/json",
		type: "GET",
		complete: function(data)
		{
			callbackFunction(JSON.parse(data.responseText));
		}
	})
}

function deleteComment(comment_id, prof_id)
{
	console.log('Comment to delete: ' + comment_id);
	console.log('Prof to delete:' + prof_id);
	
	$.ajax({
		url:"rest/comments/delete-comment/" + comment_id,
		dataType:"application/json",
		type:"GET",
		complete:function(data)
		{
			refreshProfInfo(data, prof_id);
		}
	});
	
}

function sendComment(prof_id, username)
{
	var id = new Date().getTime();
	var prof_grade = $("#prof-profer").val();
	var speaker_grade = $("#prof-speaker").val();
	var grader_grade = $("#prof-grader").val();
	var comment = $("#prof-comment").val();
	
	comment_object = {
			profId: prof_id,
			textMessage: comment,
			prof_grade: prof_grade,
			pred_grade: speaker_grade,
			ocen_grade: grader_grade,
			usernamePosted: username,
			id: id
	}
	
	$.ajax({
		url:"rest/comments/post-new-comment",
		dataType:'application/json',
		contentType:'application/json',
		data:JSON.stringify(comment_object),
		type:"POST",
		complete:function(data){
			refreshProfInfo(data, prof_id);
		}
	});
}

function displayProfInfo(prof_id)
{
	var pth = 'rest/profs/get-prof-by-id/' + prof_id;
	$.ajax({
		url:pth,
		dataType:'application/json',
		type:"GET",
		complete:function(data)
		{
			refreshProfInfo(data, prof_id);
		}
	});
}

function refreshProfInfo(data, prof_id)
{
	var logged_user = JSON.parse(localStorage.getItem("logged-user"));
	var can_comment = true;
	var is_admin = false;
	
	var allInfo = JSON.parse(data.responseText);
	var profInfo = allInfo.prof;
	var comments = allInfo.comments;
	var comment_wrapped = allInfo.commentWrappers;
	
	if ( logged_user != null)
	{
		is_admin = logged_user.user_role == 0;
		for ( var idx in comments)
		{
			if (comments[idx].usernamePosted == logged_user.username)
			{
				can_comment = false;
				break;
			}
		}
	}
	else
	{
		can_comment = false;
	}
	
	var prof_grade = 0;
	var pred_grade = 0;
	var ocen_grade = 0;
	
	if (comments.length != 0)
	{
		for (var comment_idx in comments)
		{
			prof_grade += comments[comment_idx].prof_grade;
			pred_grade += comments[comment_idx].pred_grade;
			ocen_grade += comments[comment_idx].ocen_grade;
		}
		
		prof_grade /= comments.length;
		pred_grade /= comments.length;
		ocen_grade /= comments.length;			
	}
	
	var prof_name_surname_subject="<b>" + profInfo.name + "</b>" + " " + "<b>" + profInfo.surname + "</b>" + " држи предмет " + "<b>" + profInfo.subject + "</b>";
	
	//============================================
	// HEADING TABLE
	//============================================
	var generated_header_table = "" +
			"<table class=\"table text-center\">" +
			"	<tr>" +
			"		<th  colspan=\"3\" align=\"right\">Просечне оцене:</th>" +
			"	</tr>" +
			"	<tr>" +
			"		<th>Професор:</th>" +
			"		<th>Предавач:</th>" +
			"		<th>Оцењивач:</th>" +
			"	</tr>" +
			"	<tr>" +
			"		<td>" + prof_grade + "</td>" +
			"		<td>" + pred_grade + "</td>" +
			"		<td>" + ocen_grade + "</td>" +
			"	</tr>" +
			"</table>";
	
	//============================================
	//	COMMENTS TABLE
	//============================================
	var all_comment_rows = "";
	for (var item_idx in comment_wrapped)
	{
		var com = comment_wrapped[item_idx];
		console.log(com);
		all_comment_rows += "<tr>" +
				"	<td>" + com.usernamePosted + "</td>" +
				"	<td>" + com.profGrade+ "</td>" +
				"	<td>" + com.predGrade+ "</td>" +
				"	<td>" + com.ocenGrade+ "</td>" +
				"	<td>" + com.comment+ "</td>";
		
		if ( is_admin )
		{
			all_comment_rows += "<td><button onclick=\"deleteComment("+com.commentId+", "+prof_id+")\">Обриши коментар</button></td>"
		}
		
		all_comment_rows += "</tr>";
	}
	
	var generated_comments_table ="" +
	"<table class=\"table table-striped table-bordered table-hover table-condensed\">" +
	"	<tr>" +
	"		<th>Корисничко име</th>" +
	"		<th>Оцена Професор</th>" +
	"		<th>Оцена Предавач</th>" +
	"		<th>Оцена Оцењивач</th>" +
	"		<th>Коментар</th>" +
	"	</tr>" +
	"" + all_comment_rows +
	"</table>";
			
	//============================================
	// FULL PAGE INFO
	//============================================
	var generated_page_info = "" +
	"		<img class=\"img-rounded\" width=\"200px\" height=\"200px\" src=\"" + profInfo.imageBase64 + "\">" + "<br><br>" +
	"<div class=\"panel panel-default\">" +
	"	<div class=\"panel-heading\">" +
	"		<h1 class=\"panel-title\">" + prof_name_surname_subject + "</h1></div>" +
	"	<div class=\"panel-body\">" +
	"		" + generated_header_table +  generated_comments_table +
	"	</div>" +
	"	<div class=\"panel-footer\">";
	
	if (can_comment)
	{
	generated_page_info +=	"		<form id=\"comment-form\">" +
		"			<div class=\"form-group\">" +
		"				<label for=\"prof-profer\">Професор:</label>" +
		"				<select class=\"form-control\" id=\"prof-profer\">" +
		"					<option value=\"1\">1</option>" +
		"					<option value=\"2\">2</option>" +
		"					<option value=\"3\">3</option>" +
		"					<option value=\"4\">4</option>" +
		"					<option value=\"5\" selected=\"selected\">5</option>" +
		"				</select>" +
		"			</div>" +
		"			<div class=\"form-group\">" +
		"				<label for=\"prof-speaker\">Предавач:</label>" +
		"				<select class=\"form-control\" id=\"prof-speaker\">" +
		"					<option value=\"1\">1</option>" +
		"					<option value=\"2\">2</option>" +
		"					<option value=\"3\">3</option>" +
		"					<option value=\"4\">4</option>" +
		"					<option value=\"5\" selected=\"selected\">5</option>" +
		"				</select>" +
		"			</div>" +
		"			<div class=\"form-group\">" +
		"				<label for=\"prof-grader\">Оцењивач:</label>" +
		"				<select class=\"form-control\" id=\"prof-grader\">" +
		"					<option value=\"1\">1</option>" +
		"					<option value=\"2\">2</option>" +
		"					<option value=\"3\">3</option>" +
		"					<option value=\"4\">4</option>" +
		"					<option value=\"5\" selected=\"selected\">5</option>" +
		"				</select>" +
		"			</div>" +
		"			<div class=\"form-group\">" +
		"				<label for=\"prof-comment\">Коментар:</label>" +
		"				<textarea class=\"form-control\" rows=\"5\" id=\"prof-comment\"></textarea>" +
		"			</div>" +
		"		</form>" +
		"		<button class=\"btn btn-default\" onclick=\"sendComment(" + prof_id + ", '" + logged_user.username + "')\">Posalji Komentar</button>";
	}
	
	generated_page_info += "	</div>" +
	"</div>";
	
	$("#main-content-div").html(generated_page_info);
}
