<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Image Display</title>
    <script>
        function loadImage() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', '/JsonImageServlet?id=1', true);
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    var img = document.getElementById('image');
                    img.src = "data:image/png;base64," + response.imageData;
                }
            };
            xhr.send();
        }
    </script>
</head>
<body onload="loadImage()">
    <img id="image" alt="Image">
</body>
</html>
