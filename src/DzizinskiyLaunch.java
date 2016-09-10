import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

 
public class DzizinskiyLaunch extends TelegramLongPollingBot {
	
 
	public static void main(String[] args) {
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new DzizinskiyLaunch());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public String getBotUsername() {
		return "Dzizinskiybot";
	}
 
	@Override
	public String getBotToken() {
		return "283093949:AAFwn-fM4P7ndMhntYfc_I2bD4jfRQhXOvY";
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		Message message = update.getMessage();
		if (message != null && message.hasText()) {
			if (message.getText().equals("/допомога")){
				sendMsg(message, "Привіт, я Діма ☺️");
			}
			else if (message.getText().contains("година")){
				sendMsg(message, timeNow());
			}
			else if (message.getText().equals("/пари")){
				sendMsg(message, universityGo());
			} else {
				sendMsg(message, "Полайкай мені фотки в інстаграмі @dzizinskiy");
			} 
			
		}
	}
 
	private void sendMsg(Message message, String text) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(message.getChatId().toString());
		sendMessage.setReplayToMessageId(message.getMessageId());
		sendMessage.setText(text);
		try {
			sendMessage(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	private String timeNow() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(cal.getTime()) ;
		return time;
	}
	
	private String universityGo() {
		String words = "Йти:Не йти:Дивлячись чи є Міхалевич";
		String[] wordsAsArray = words.split(":");
		
		int index = new Random().nextInt(wordsAsArray.length);
		String goOrNotToGo = wordsAsArray[index];

		return goOrNotToGo;
	}
 
}