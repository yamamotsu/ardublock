package com.ardublock.translator.block.plaquino;

/* おまじない */
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
import com.ardublock.translator.block.TranslatorBlock;

public class PQLEDOn extends TranslatorBlock
{

	/* コンストラクタ: 引数と基底クラスコンストラクタの呼び出しはテンプレパターン*/
	public PQLEDOn(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
        translator.addDefinitionCommand("const int led_on = HIGH;\n");

        return codePrefix + "led_on" + codeSuffix;
	}
}
