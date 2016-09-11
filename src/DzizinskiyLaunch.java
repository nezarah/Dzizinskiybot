import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

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

	String[][] dimaChat = {
			//привітання
			{ "прив", "хай", "ку", "здоров", "добр" },
			{ "привіт, я Діма, як тебе звати?☺️", "привіт, я Діма, ти фрешка?☺️", "хаю хай☺️" },
			// справи
			{ "справи", "почуваєшся", "робиш" , "робити"},
			{ "чудово, щойно з Ауруму прийшов", "та готуюсь до Міхалевича" },
			// років
			{ "рік", "років" }, { "Я вже у мами дорослий — мені 20" },
			// різні ситуації
			{ "навчання" }, { "та забий на навчання" },
			// різні ситуації
			{ "кораблик" }, { "о, пішли зі мною на кораблик" },
			// різні ситуації
			{ "коли" }, { "ой, ти знову про це" },
			{ "глибовець" }, { "Найкращий викладач у світі☺️" },
			// дефолт
			{"Полайкай мені фотки в інстаграмі @dzizinskiy",
					"Твої батьки не програмісти? тоді звідки у них така фіча" } };
	
	@Override
	public void onUpdateReceived(Update update) {
		Message message = update.getMessage();
		if (message != null && message.hasText()) {
			
			String msg = message.getText().toLowerCase();
			String[] parts = msg.split(" ");
			
			for (int p = 0; p <= parts.length; p++){
				for(int i = 0; i < dimaChat.length; i+=2){
					for(int j = 0; j < dimaChat[i].length; j++){
						if(parts[p].contains(dimaChat[i][j])){
							int k = new Random().nextInt(dimaChat[i+1].length);
							sendMsg(message, dimaChat[i+1][k]);
						}
					}
				}
			}
			sendMsg(message, "def");
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

}