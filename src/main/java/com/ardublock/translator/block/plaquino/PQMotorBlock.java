package com.ardublock.translator.block.plaquino;

/* おまじない */
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
import com.ardublock.translator.block.TranslatorBlock;

public class PQMotorBlock extends TranslatorBlock
{

	/* コンストラクタ: 引数と基底クラスコンストラクタの呼び出しはテンプレパターン*/
	public PQMotorBlock(Long blockId, Translator translator,
			String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		//↓ラベルの名前を取得する　(使わない)
		//String subroutineName = label.trim();

		// モーター関数を定義
		translator.addDefinitionCommand(PQMotorFunctions);

		// setup()内に書いておく処理
		translator.addSetupCommand("pinMode(pin_motorl_IN1, OUTPUT);\n");
		translator.addSetupCommand("pinMode(pin_motorl_IN2, OUTPUT);\n");
		translator.addSetupCommand("pinMode(pin_motorl_VREF, OUTPUT);\n");
		translator.addSetupCommand("pinMode(pin_motorr_IN1, OUTPUT);\n");
		translator.addSetupCommand("pinMode(pin_motorr_IN2, OUTPUT);\n");
		translator.addSetupCommand("pinMode(pin_motorl_VREF, OUTPUT);\n");

		//ソケットにつながったブロックを取得(左モーターのスピード)
		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(0);
		//intに変換
		int left_speed = Integer.parseInt(translatorBlock.toCode());

		//ソケットにつながったブロックを取得(右モーターのスピード)
		translatorBlock = getTranslatorBlockAtSocket(1);
		//intに変換
		int right_speed = Integer.parseInt(translatorBlock.toCode());

		return "pq_motor(" + left_speed + " ," + right_speed + ");\n";
	}

	private static final String PQMotorFunctions =
		"const byte pin_motorl_IN1 = 10;\n" +
		"const byte pin_motorl_IN2 = 9;\n" +
		"const byte pin_motorl_VREF = 11;\n" +
		"const byte pin_motorr_IN1 = 7;\n" +
		"const byte pin_motorr_IN2 = 8;\n" +
		"const byte pin_motorr_VREF = 6;\n" +
		"\n"+
		"void pq_motor(int speed_l, int speed_r) {\n" +
		"	if(speed_l >= 0)\n" +
		"	{\n" +
		"		if(speed_l > 100) speed_l = 100;\n" +
		"		digitalWrite(pin_motorl_IN1, HIGH);\n" +
		"		digitalWrite(pin_motorl_IN2, LOW);\n" +
		"		analogWrite(pin_motorl_VREF, byte(speed_l * 2.55));\n" +
		"	}\n" +
		"	else\n" +
		"	{\n" +
		"		if(speed_l < -100) speed_l = -100;\n" +
		"		digitalWrite(pin_motorl_IN1, LOW);\n" +
		"		digitalWrite(pin_motorl_IN2, HIGH);\n" +
		"		analogWrite(pin_motorl_VREF, byte(-speed_l * 2.55));\n" +
		"	}\n" +
		"\n"+
		"	if(speed_r >= 0)\n" +
		"	{\n" +
		"		if(speed_r > 100) speed_r = 100;\n" +
		"		digitalWrite(pin_motorr_IN1, LOW);\n" +
		"		digitalWrite(pin_motorr_IN2, HIGH);\n" +
		"		analogWrite(pin_motorr_VREF, byte(speed_r * 2.55));\n" +
		"	}\n" +
		"	else\n" +
		"	{\n" +
		"		if(speed_r < -100) speed_r = -100;\n" +
		"		digitalWrite(pin_motorr_IN1, HIGH);\n" +
		"		digitalWrite(pin_motorr_IN2, LOW);\n" +
		"		analogWrite(pin_motorr_VREF, byte(-speed_r * 2.55));\n" +
		"	}\n" +
		"}\n";
}
