document.getElementById("submit").addEventListener("click", () => {

  fetch('/evaluate', {
    headers: {
      'Content-Type': 'application/json'
    },
    method: 'POST',
    body: JSON.stringify({
      expression: document.getElementById("input").value
    })
  }).then(res => {
    console.log(res);
    return res.json();
  }).then(data => {
    console.log(data);
    if (data.expression === null) {
      document.getElementById("result").innerHTML = "500: Internal Server Error";
    } else {
      document.getElementById("result").innerHTML = data.expression;
    }

  }).catch(err => {
    console.log(err);
  });

});