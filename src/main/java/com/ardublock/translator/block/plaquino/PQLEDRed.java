package com.ardublock.translator.block.plaquino;

/* おまじない */
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
import com.ardublock.translator.block.TranslatorBlock;

public class PQLEDRed extends TranslatorBlock
{

	/* コンストラクタ: 引数と基底クラスコンストラクタの呼び出しはテンプレパターン*/
	public PQLEDRed(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
        translator.addDefinitionCommand("const byte pin_led_red = 2; //左LED\n");
        // setup()内に書いておく処理
        translator.addSetupCommand("pinMode(pin_led_red, OUTPUT);\n");

        return codePrefix + "pin_led_red" + codeSuffix;
	}
}
