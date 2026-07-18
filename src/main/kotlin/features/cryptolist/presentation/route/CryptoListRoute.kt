package com.behnamdev.features.cryptolist.presentation.route

import com.behnamdev.features.crypto.domain.service.GetCryptoUseCase
import com.behnamdev.features.cryptolist.domain.service.GetCryptoListUseCase
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import kotlin.getValue

fun Route.cryptoListRouting() {
    val getCryptoListUseCase by inject<GetCryptoListUseCase>()
    val getCryptoUseCase by inject<GetCryptoUseCase>()

    get("/") {
        val htmlContent = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Crypto Live Dashboard</title>
            <style>
                * {
                    margin: 0;
                    padding: 0;
                    box-sizing: border-box;
                }
                body {
                    background-color: #080c14;
                    color: #c9d1d9;
                    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
                    padding: 2rem;
                    min-height: 100vh;
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                }
                .header {
                    text-align: center;
                    margin-bottom: 2.5rem;
                    background: rgba(13, 17, 23, 0.7);
                    padding: 1.5rem 3rem;
                    border: 1px solid #21262d;
                    border-radius: 16px;
                    backdrop-filter: blur(8px);
                    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.4);
                }
                h1 {
                    font-size: 2.5rem;
                    color: #00ff66;
                    margin-bottom: 0.5rem;
                    letter-spacing: 1px;
                    text-shadow: 0 0 15px rgba(0, 255, 102, 0.2);
                }
                .status-container {
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    gap: 10px;
                }
                .pulse-dot {
                    width: 8px;
                    height: 8px;
                    background-color: #00ff66;
                    border-radius: 50%;
                    animation: pulse 1.6s infinite;
                }
                .crypto-grid {
                    display: grid;
                    grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
                    gap: 1.5rem;
                    width: 100%;
                    max-width: 1200px;
                    padding: 10px;
                }
                .crypto-card {
                    background: linear-gradient(145deg, #0e1420, #131b2e);
                    border: 1px solid #223049;
                    border-radius: 14px;
                    padding: 1.5rem;
                    display: flex;
                    flex-direction: column;
                    justify-content: space-between;
                    transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
                    position: relative;
                    overflow: hidden;
                    box-shadow: 0 4px 20px rgba(0,0,0,0.3);
                }
                .crypto-card::before {
                    content: '';
                    position: absolute;
                    top: 0; left: 0; width: 100%; height: 4px;
                    background: linear-gradient(90deg, #00ff66, #00bfff);
                    opacity: 0.7;
                }
                .crypto-card:hover {
                    transform: translateY(-5px);
                    border-color: #00ff66;
                    box-shadow: 0 8px 25px rgba(0, 255, 102, 0.15);
                }
                .card-header {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    margin-bottom: 1rem;
                }
                .symbol-badge {
                    background: rgba(0, 255, 102, 0.1);
                    color: #00ff66;
                    padding: 4px 10px;
                    border-radius: 8px;
                    font-weight: bold;
                    font-size: 0.9rem;
                    border: 1px solid rgba(0, 255, 102, 0.2);
                }
                .crypto-name {
                    font-size: 1.25rem;
                    font-weight: 600;
                    color: #ffffff;
                }
                .source-tag {
                    font-size: 0.8rem;
                    color: #8b949e;
                    text-transform: uppercase;
                    background: #1f293d;
                    padding: 2px 6px;
                    border-radius: 4px;
                }
                .card-details {
                    border-top: 1px solid #223049;
                    padding-top: 0.8rem;
                    margin-top: 0.8rem;
                    display: flex;
                    flex-direction: column;
                    gap: 6px;
                    font-size: 0.85rem;
                    color: #8b949e;
                }
                .detail-row {
                    display: flex;
                    justify-content: space-between;
                }
                .detail-val {
                    color: #e6edf3;
                    font-family: monospace;
                }
                .loader {
                    color: #8b949e;
                    font-size: 1.2rem;
                    margin-top: 5rem;
                }
                @keyframes pulse {
                    0% { transform: scale(0.95); box-shadow: 0 0 0 0 rgba(0, 255, 102, 0.5); }
                    70% { transform: scale(1); box-shadow: 0 0 0 8px rgba(0, 255, 102, 0); }
                    100% { transform: scale(0.95); box-shadow: 0 0 0 0 rgba(0, 255, 102, 0); }
                }
            </style>
        </head>
        <body>
            <div class="header">
                <h1>Crypto Dashboard</h1>
                <div class="status-container">
                    <div class="pulse-dot"></div>
                    <span style="color: #00ff66; font-weight: 500; font-size: 0.95rem;">Live Gateway Active</span>
                </div>
            </div>

            <div id="loader" class="loader">Fetching market assets...</div>
            <div class="crypto-grid" id="crypto-grid"></div>

            <script>
                async function fetchCryptoList() {
                    try {
                        const response = await fetch('/cryptoList');
                        const data = await response.json();
                        
                        document.getElementById('loader').style.display = 'none';
                        const grid = document.getElementById('crypto-grid');
                        
                        data.forEach(coin => {
                            const card = document.createElement('div');
                            card.className = 'crypto-card';
                            card.innerHTML = `
                                <div class="card-header">
                                    <span class="crypto-name">${"$"}{coin.name || coin.symbol}</span>
                                    <span class="symbol-badge">${"$"}{coin.symbol}</span>
                                </div>
                                <div class="detail-row">
                                    <span>Source Platform</span>
                                    <span class="source-tag">${"$"}{coin.source}</span>
                                </div>
                                <div class="card-details">
                                    <div class="detail-row">
                                        <span>OHLC Available:</span>
                                        <span class="detail-val">${"$"}{coin.ohlc_available_from}</span>
                                    </div>
                                    <div class="detail-row">
                                        <span>History From:</span>
                                        <span class="detail-val">${"$"}{coin.history_available_from}</span>
                                    </div>
                                </div>
                            `;
                            grid.appendChild(card);
                        });
                    } catch (error) {
                        document.getElementById('loader').innerText = 'Failed to load crypto assets.';
                        console.error(error);
                    }
                }
                fetchCryptoList();
            </script>
        </body>
        </html>
    """.trimIndent()

        call.respondText(
            text = htmlContent,
            contentType = ContentType.Text.Html
        )
    }

    get("/cryptoList") {
        val result = getCryptoListUseCase.execute()
        call.respond(result)
    }
    get("/{symbol}") {
        val result = getCryptoUseCase.execute(symbol = call.parameters["symbol"] ?: "")
        call.respondText(
            text = result.get(0).last,
            status = HttpStatusCode.OK
        )
    }

}