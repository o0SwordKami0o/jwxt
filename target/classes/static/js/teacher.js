function selectScore(id){
    window.location.href="/teacher/score?id="+id;
}
function showScore(cid,sid){
    window.location.href="/teacher/score/show?cid="+cid+"&sid="+sid;
}
function addScore(cid){
    window.location.href="/teacher/score/add?cid="+cid;
}
function page(pn,id){
    window.location.href="/teacher/score?pn="+pn+"&id="+id;
}
function choose(id,stepId){
    window.location.href="/student/choose?id="+id+"&stepId="+stepId;
}
function back(id,stepId){
    window.location.href="/student/back?id="+id+"&stepId="+stepId;
}
