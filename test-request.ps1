$headers = @{
    "Content-Type" = "application/json"
    "Accept" = "application/json"
}

$body = @{
    name = "Test Order"
    description = "This is a test order"
    createDate = "2025-04-25"
} | ConvertTo-Json

Write-Host "Making POST request to http://localhost:8080/orders/create"
Write-Host "Headers:"
$headers | Format-Table -AutoSize
Write-Host "Body:"
$body

$response = Invoke-WebRequest -Method Post -Uri "http://localhost:8080/orders/create" -Headers $headers -Body $body -UseBasicParsing

Write-Host "`nResponse Status Code: $($response.StatusCode)"
Write-Host "Response Headers:"
$response.Headers | Format-Table -AutoSize
Write-Host "Response Body:"
$response.Content
