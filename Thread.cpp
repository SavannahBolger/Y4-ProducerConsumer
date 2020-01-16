#include <iostream>
#include <thread>
#include <chrono>

int p = 0;
int c = 0;
const int n = 5;

void producer()
{
	int a[n] = {10,20,30,40,50};
	bool run = true;
	while(run)
	{
		while (p < n)
		{
			std::cout << 
		}
		
	}
}

void consumer()
{
	while(true)
	{
		std::cout << "Process ID: " << std::this_thread::get_id() << std::endl;
		std::this_thread::sleep_for(std::chrono::seconds(1));
	}
}

int main(void)
{
	std::thread producer(producer);
	std::thread consumer(consumer);
	producer.join();
	consumer.join();
	std::cin.get();
}