pip install requests pandas matplotlib
import requests
import pandas as pd
import matplotlib.pyplot as plt
from datetime import datetime

# Function to get Bitcoin price data from CoinGecko API
def get_bitcoin_data():
    url = 'https://api.coingecko.com/api/v3/coins/bitcoin/market_chart'
    params = {
        'vs_currency': 'usd',
        'days': '30',  # Get data for the last 30 days
        'interval': 'daily'  # Daily data points
    }
    response = requests.get(url, params=params)
    data = response.json()
    return data

# Fetch Bitcoin price data
data = get_bitcoin_data()

# Extract prices and timestamps
prices = data['prices']
timestamps = [datetime.utcfromtimestamp(item[0] / 1000) for item in prices]
price_values = [item[1] for item in prices]

# Create a DataFrame
df = pd.DataFrame({
    'Timestamp': timestamps,
    'Price (USD)': price_values
})

# Set timestamp as index
df.set_index('Timestamp', inplace=True)

# Basic data exploration
print("First few rows of data:")
print(df.head())

print("\nDescriptive statistics:")
print(df.describe())

# Visualizing the Bitcoin prices
plt.figure(figsize=(10,6))
plt.plot(df.index, df['Price (USD)'], label="Bitcoin Price (USD)")
plt.title("Bitcoin Price in the Last 30 Days")
plt.xlabel("Date")
plt.ylabel("Price (USD)")
plt.xticks(rotation=45)
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()

# Additional analysis - Calculating daily returns
df['Daily Return'] = df['Price (USD)'].pct_change()

# Visualizing daily returns
plt.figure(figsize=(10,6))
plt.plot(df.index, df['Daily Return'], label="Daily Returns", color='red')
plt.title("Bitcoin Daily Returns in the Last 30 Days")
plt.xlabel("Date")
plt.ylabel("Daily Return")
plt.xticks(rotation=45)
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()

# Calculate average price and volatility
average_price = df['Price (USD)'].mean()
volatility = df['Daily Return'].std()

print(f"\nAverage Price of Bitcoin in the Last 30 Days: ${average_price:.2f}")
print(f"Bitcoin Price Volatility (Standard Deviation of Daily Returns): {volatility:.4f}")
